package vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.RefreshTokenEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JPARefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    Optional<RefreshTokenEntity> findByTokenHash(byte[] tokenHash);

    List<RefreshTokenEntity> findAllByFamilyId(UUID familyId);

}
