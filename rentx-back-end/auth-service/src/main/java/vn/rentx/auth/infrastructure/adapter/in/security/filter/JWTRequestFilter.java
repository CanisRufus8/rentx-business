package vn.rentx.auth.infrastructure.adapter.in.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.rentx.auth.infrastructure.adapter.in.security.provider.JWTProvider;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;


@RequiredArgsConstructor
@Slf4j
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JWTProvider jwtProvider;

    public static final Set<String> PUBLIC_PATHS = Set.of(
            "/api/auth/login",
            "/api/users/command/register",
            "/api/auth/logout"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return PUBLIC_PATHS.contains(request.getServletPath());
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("ServletPath={}, RequestURI={}", request.getServletPath(), request.getRequestURI());

        try {
            log.info("JWTRequestFilter.doFilterInternal");
            getJwtFromRequest(request)
                    .flatMap(jwtProvider::validateTokenAndGetJws)
                    .ifPresent(jws -> {
                        String username = jws.getPayload().getSubject();
                        log.info("UserDetails userDetails = userDetailsService.loadUserByUsername(username);");
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });
        } catch (Exception e) {
            log.error("Cannot set user authentication", e);
        }

        log.info("JWTRequestFilter.doFilterInternal -- 2");
        filterChain.doFilter(request, response);
    }

    private Optional<String> getJwtFromRequest(HttpServletRequest request) {

        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("JWTRequestFilter.getJwtFromRequest");
        if (StringUtils.hasText(tokenHeader) && tokenHeader.startsWith(TOKEN_PREFIX)) {
            return Optional.of(tokenHeader.replace(TOKEN_PREFIX, ""));
        }

        return Optional.empty();
    }

    public static final String TOKEN_PREFIX = "Bearer ";

}
