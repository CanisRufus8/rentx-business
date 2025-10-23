package vn.rentx.auth.infrastructure.adapter.out.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.port.out.TokenRepositoryPort;
import vn.rentx.auth.domain.auth.aggregate.RefreshToken;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.entity.RefreshTokenEntity;
import vn.rentx.auth.infrastructure.adapter.out.persistence.jpa.repository.JPARefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenRepositoryAdapter implements TokenRepositoryPort {

    private final JPARefreshTokenRepository jpaTokenRepository;

    @Override
    public void saveRefreshToken(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity().toEntity(refreshToken);
        jpaTokenRepository.save(refreshTokenEntity);
    }

    @Override
    public RefreshToken findRefreshTokenByTokenHash(byte[] hash) {
        return jpaTokenRepository.findByTokenHash(hash).orElseThrow().toDomain();
    }

}
