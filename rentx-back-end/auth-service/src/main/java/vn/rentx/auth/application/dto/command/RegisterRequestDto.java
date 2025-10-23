package vn.rentx.auth.application.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import vn.rentx.auth.shared.validation.annotation.CheckEmailExists;
import vn.rentx.auth.shared.validation.annotation.CheckUsernameExists;

import java.util.Set;

public record RegisterRequestDto(

        @NotNull(message = "Full name is mandatory.")
        @Size(min = 6, max = 100, message = "From 6 to 32 characters in length.")
        String fullName,

        @NotNull(message = "Username is mandatory.")
        @Size(min = 6, max = 32, message = "From 6 to 32 characters in length.")
        @CheckUsernameExists(message = "Username is already in use.")
        String username,

        @Size(min = 11, max = 100, message = "From 11 to 100 characters in length.")
        @CheckEmailExists(message = "Email is already in use.")
        String email,

        @NotNull(message = "Password is mandatory.")
        String password,

        @NotNull(message = "Confirm password is mandatory.")
        String confirmPassword,

        Set<String> roles

) {
}