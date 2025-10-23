package vn.rentx.auth.application.port.out;

import vn.rentx.auth.domain.user.aggregate.AppUser;

import java.util.Set;

public interface UserRepositoryPort {

    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    AppUser save(AppUser user, Set<String> roles);

    AppUser findByUsername(String username);

}
