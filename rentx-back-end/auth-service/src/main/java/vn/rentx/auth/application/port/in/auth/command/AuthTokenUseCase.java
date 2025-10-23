package vn.rentx.auth.application.port.in.auth.command;

import vn.rentx.auth.application.dto.response.AuthTokenResponseDto;

import java.util.UUID;

public interface AuthTokenUseCase {

    AuthTokenResponseDto generateTokensForUser(String username, UUID userId, String fingerPrint);

}
