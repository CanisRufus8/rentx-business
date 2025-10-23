package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.RoleEntity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface JPAUserRoleRepository extends JpaRepository<RoleEntity, UUID> {

    List<RoleEntity> findByNameIn(Collection<String> names);

}
