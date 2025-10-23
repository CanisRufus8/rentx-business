package vn.rentx.auth.application.port.in.user.query;

import vn.rentx.auth.application.dto.response.UserProfileResponseDto;
import vn.rentx.auth.domain.user.aggregate.AppUser;

public interface QueryUserUseCase {

    UserProfileResponseDto getUserProfile(String username);

    AppUser getUserInfo(String username);

}
