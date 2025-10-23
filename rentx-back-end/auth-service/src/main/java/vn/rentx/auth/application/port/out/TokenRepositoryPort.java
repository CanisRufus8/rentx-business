package vn.rentx.auth.application.port.out;

import vn.rentx.auth.domain.auth.aggregate.RefreshToken;

public interface TokenRepositoryPort {

    void saveRefreshToken(RefreshToken refreshToken);

    RefreshToken findRefreshTokenByTokenHash(byte[] hash);

}
