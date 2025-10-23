package vn.rentx.auth.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.port.out.UserRepositoryPort;
import vn.rentx.auth.domain.user.aggregate.AppUser;
import vn.rentx.auth.domain.user.exception.UserNotFoundException;
import vn.rentx.auth.domain.user.vo.UserRole;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.RoleEntity;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.mapper.auto.UserMapper;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.mapper.manual.UserRoleConverter;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.repository.JPAUserRepository;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.repository.JPAUserRoleRepository;
import vn.rentx.auth.shared.constant.RentXMessageConst;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JPAUserRepository jpaUserRepository;
    private final JPAUserRoleRepository jpaUserRoleRepository;
    private final UserMapper userMapper;
    private final UserRoleConverter userRoleConverter;

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public AppUser save(AppUser appUser, Set<String> roles) {
        UserEntity userEntity = userMapper.toEntity(appUser);
        // Get role from DB by input
        Set<RoleEntity> roleEntities = new HashSet<>(jpaUserRoleRepository.findByNameIn(roles));
        userEntity.setRoles(roleEntities);
        return userMapper.toDomain(jpaUserRepository.save(userEntity));
    }

    @Override
    public AppUser findByUsername(String username) {
        UserEntity userEntity = jpaUserRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(String.format(RentXMessageConst.USER_NOT_FOUND, username)));
        AppUser appUser = userMapper.toDomain(userEntity);
        Set<UserRole> userRoles = userEntity.getRoles()
                .stream()
                .map(RoleEntity::toDomain)
                .collect(Collectors.toSet());
        appUser.setUserRoles(userRoles);
        return appUser;
    }

}
