package vn.rentx.auth.infrastructure.adapter.in.security.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.rentx.auth.infrastructure.adapter.in.security.filter.JWTRequestFilter;
import vn.rentx.auth.infrastructure.adapter.in.security.handler.CustomLogoutSuccessHandler;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WebSecurityConfig {

    private final AuthenticationConfiguration authConfiguration;
    private final JWTRequestFilter jwtRequestFilter;


    private static final String[] PUBLIC_END_POINTS = {"/api/users/command/register", "/api/auth/login", "/api/auth/logout"};
    private static final String ADMIN_FEATURE_PATH = "/features/admin/**";
    private static final String LOG_OUT_PATH = "/logout";

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, PUBLIC_END_POINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, ADMIN_FEATURE_PATH).hasRole(ADMIN)
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class) // dùng bean
                .logout(logout -> logout
                        .logoutUrl(LOG_OUT_PATH)
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            System.out.println("Authentication failed for: " + request.getRequestURI());
            System.out.println("Error: " + authException.getMessage());

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().println("{ \"error\": \"Unauthorized\", \"message\": \"" + authException.getMessage() + "\" }");
        };
    }


}
