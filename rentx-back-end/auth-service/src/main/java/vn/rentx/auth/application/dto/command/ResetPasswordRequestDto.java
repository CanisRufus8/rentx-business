package vn.rentx.auth.application.dto.command;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordRequestDto {
    @NotBlank
    private String token;
    @NotBlank
    private String password;
}
