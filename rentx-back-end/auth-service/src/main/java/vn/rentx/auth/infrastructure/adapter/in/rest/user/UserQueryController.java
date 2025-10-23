package vn.rentx.auth.infrastructure.adapter.in.rest.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.rentx.auth.application.port.in.user.command.RegisterUserUseCase;
import vn.rentx.auth.application.port.in.user.query.QueryUserUseCase;

@RestController
@RequestMapping("/api/users/query")
@RequiredArgsConstructor
public class UserQueryController {

    private final QueryUserUseCase queryUserUseCase;

    @GetMapping(value = "/info/{username}")
    public ResponseEntity<?> register(@Valid @PathVariable String username) {
        System.out.println("username: " + username);
        return ResponseEntity.ok(queryUserUseCase.getUserInfo(username));
    }
}
