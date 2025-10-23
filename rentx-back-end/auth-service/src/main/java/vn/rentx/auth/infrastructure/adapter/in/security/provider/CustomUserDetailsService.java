package vn.rentx.auth.infrastructure.adapter.in.security.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.port.out.UserRepositoryPort;
import vn.rentx.auth.domain.user.aggregate.AppUser;
import vn.rentx.auth.infrastructure.adapter.in.security.userdetails.CustomUserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String username) {
//        log.info("CustomUserDetailsService.loadUserByUsername--001");
        System.out.println("CustomUserDetailsService.loadUserByUsername");
        AppUser appUser = userRepositoryPort.findByUsername(username);
        Set<SimpleGrantedAuthority> authorities = appUser.getUserRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getValue())).collect(Collectors.toSet());

//        log.info("CustomUserDetailsService.loadUserByUsername--002");
        return mapUserToCustomUserDetails(appUser, authorities);
    }

    private CustomUserDetails mapUserToCustomUserDetails(AppUser user, Set<SimpleGrantedAuthority> authorities) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUserId(user.getId());
        customUserDetails.setUsername(user.getUsername().getValue());
        customUserDetails.setEmail(user.getEmail().getValue());
        customUserDetails.setPassword(user.getPassword().getHashedValue());
        customUserDetails.setAuthorities(authorities);

//        log.info("CustomUserDetailsService.mapUserToCustomUserDetails");
        return customUserDetails;
    }

}