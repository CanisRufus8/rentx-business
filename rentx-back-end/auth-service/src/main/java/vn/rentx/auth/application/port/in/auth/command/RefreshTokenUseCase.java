package vn.rentx.auth.application.port.in.auth.command;

import java.util.UUID;

public interface RefreshTokenUseCase {

    String createRefreshToken(UUID userId, String fingerprint);

}
