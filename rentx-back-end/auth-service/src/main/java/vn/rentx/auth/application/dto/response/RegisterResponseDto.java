package vn.rentx.auth.application.dto.response;

import lombok.Builder;

@Builder
public record RegisterResponseDto(

        String status,
        String message
) {
}
