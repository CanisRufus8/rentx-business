package vn.rentx.auth.application.service.auth.query;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.rentx.auth.application.dto.response.UserProfileResponseDto;
import vn.rentx.auth.application.port.in.user.query.QueryUserUseCase;
import vn.rentx.auth.application.port.out.UserRepositoryPort;
import vn.rentx.auth.domain.user.aggregate.AppUser;

@Service
@AllArgsConstructor
public class QueryUserService implements QueryUserUseCase {

    UserRepositoryPort userRepositoryPort;


    @Override
    public UserProfileResponseDto getUserProfile(String username) {
        return null;
    }

    @Override
    public AppUser getUserInfo(String username) {
        return userRepositoryPort.findByUsername(username);
    }
}
