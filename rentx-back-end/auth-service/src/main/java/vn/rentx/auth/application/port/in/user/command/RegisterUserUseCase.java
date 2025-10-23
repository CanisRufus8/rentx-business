package vn.rentx.auth.application.port.in.user.command;

import vn.rentx.auth.application.dto.command.RegisterRequestDto;
import vn.rentx.auth.application.dto.response.RegisterResponseDto;
import vn.rentx.auth.domain.user.aggregate.AppUser;

public interface RegisterUserUseCase {

    AppUser register(RegisterRequestDto registerUserDto);

}
