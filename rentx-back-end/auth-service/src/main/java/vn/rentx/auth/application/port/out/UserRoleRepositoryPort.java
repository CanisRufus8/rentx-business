package vn.rentx.auth.application.port.out;

import vn.rentx.auth.domain.user.vo.UserRole;

import java.util.List;

public interface UserRoleRepositoryPort {

    List<UserRole> findAllByUsername(String username);

}
