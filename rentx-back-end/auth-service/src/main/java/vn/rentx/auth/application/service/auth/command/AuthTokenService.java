package vn.rentx.auth.application.service.auth.command;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.dto.response.AuthTokenResponseDto;
import vn.rentx.auth.application.port.in.auth.command.AuthTokenUseCase;
import vn.rentx.auth.application.port.in.auth.command.RefreshTokenUseCase;
import vn.rentx.auth.infrastructure.adapter.in.security.provider.JWTProvider;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthTokenService implements AuthTokenUseCase {

    private final JWTProvider jwtProvider;
    private final RefreshTokenUseCase refreshTokenUseCase;

    @Override
    public AuthTokenResponseDto generateTokensForUser(String username, UUID userId, String rawFingerprint) {

        System.out.println("generateTokensForUser");
        String jwt = jwtProvider.generateAccessToken(username);
        String refreshTokenPlain = refreshTokenUseCase.createRefreshToken(userId, rawFingerprint);

        return AuthTokenResponseDto.builder().accessToken(jwt).refreshTokenPlain(refreshTokenPlain).build();
    }

//    public AuthTokenResponseDto refreshTokens(String refreshTokenValue) {
//        // Validate refresh token
//        RefreshToken refreshToken = refreshTokenService.validateRefreshToken(refreshTokenValue);
//
//        // Generate new tokens
//        return generateTokensForUser(refreshToken.getUserId());
//    }
}
