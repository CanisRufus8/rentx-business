package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.mapper.manual;

import org.springframework.stereotype.Component;

@Component
public class UserRoleConverter {

//    public Set<Role> mapToDomainRoles(Set<UserRoleEntity> roleEntities) {
//        if (roleEntities == null) return Set.of();
//        return roleEntities.stream()
//                .map(UserRoleEntity::getRoleName)
//                .collect(Collectors.toSet());
//    }
//
//    public Set<UserRoleEntity> mapToEntities(AppUser appUser) {
//        if (appUser.getUserRoles() == null) return Set.of();
//        return appUser.getUserRoles().stream()
//                .map(role -> new UserRoleEntity(UUID.randomUUID(), appUser.getUsername().getValue(), role))
//                .collect(Collectors.toSet());
//    }

}
