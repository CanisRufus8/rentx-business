package vn.rentx.auth.infrastructure.adapter.in.rest.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.rentx.auth.application.dto.command.RegisterRequestDto;
import vn.rentx.auth.application.dto.response.AuthTokenResponseDto;
import vn.rentx.auth.application.dto.response.RegisterResponseDto;
import vn.rentx.auth.application.port.in.auth.command.AuthTokenUseCase;
import vn.rentx.auth.application.port.in.user.command.RegisterUserUseCase;
import vn.rentx.auth.domain.user.aggregate.AppUser;
import vn.rentx.auth.infrastructure.adapter.in.security.provider.JWTProvider;
import vn.rentx.auth.shared.constant.RentXCommonConst;
import vn.rentx.auth.shared.constant.RentXMessageConst;
import vn.rentx.auth.shared.utils.HttpRequestUtils;

@RestController
@RequestMapping("/api/users/command")
@RequiredArgsConstructor
public class UserCommandController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthTokenUseCase authTokenUseCase;
    private final JWTProvider jwtProvider;

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto registerRequestDto, HttpServletRequest request) {

            // Save user in database
            AppUser appUser = registerUserUseCase.register(registerRequestDto);

            // Get finger print to create refresh token
            String rawFingerprint = HttpRequestUtils.getRawFingerprint(request);

            // Create access token and refresh token plain text
            AuthTokenResponseDto authTokenResponseDto = authTokenUseCase.generateTokensForUser(appUser.getUsername().getValue(), appUser.getId(), rawFingerprint);

            // Save token in cookie
            ResponseCookie jwtCookie = jwtProvider.generateJwtCookie(authTokenResponseDto.accessToken());
            ResponseCookie jwtRefreshCookie = jwtProvider.generateRefreshJwtCookie(authTokenResponseDto.refreshTokenPlain());

            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                    .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                    .body(RegisterResponseDto.builder().status(RentXCommonConst.SUCCESS_STATUS).message(RentXMessageConst.CREATE_USER_SUCCESS).build());

    }
}
