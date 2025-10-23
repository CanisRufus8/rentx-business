package vn.rentx.auth.application.dto.response;

import lombok.Builder;

@Builder
public record AuthTokenResponseDto(

        String accessToken,
        String refreshTokenPlain

) {}