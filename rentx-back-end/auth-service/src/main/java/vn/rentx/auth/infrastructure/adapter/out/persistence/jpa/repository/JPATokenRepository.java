package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.RefreshTokenEntity;


import java.util.UUID;

public interface JPATokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {


}
