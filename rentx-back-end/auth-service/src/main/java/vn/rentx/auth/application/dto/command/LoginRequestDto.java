package vn.rentx.auth.application.dto.command;

public record LoginRequestDto(
        String username,
        String password
) {
}