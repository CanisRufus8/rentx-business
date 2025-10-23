package vn.rentx.auth.infrastructure.adapter.in.rest.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.rentx.auth.application.dto.command.LoginRequestDto;
import vn.rentx.auth.application.dto.response.AuthTokenResponseDto;
import vn.rentx.auth.application.port.in.auth.command.AuthTokenUseCase;
import vn.rentx.auth.infrastructure.adapter.in.security.provider.JWTProvider;
import vn.rentx.auth.infrastructure.adapter.in.security.userdetails.CustomUserDetails;
import vn.rentx.auth.shared.constant.RentXCommonConst;
import vn.rentx.auth.shared.utils.HttpRequestUtils;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthTokenUseCase authTokenUseCase;
    private final JWTProvider jwtProvider;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.username(), loginRequestDto.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String rawFingerprint = HttpRequestUtils.getRawFingerprint(request);

        // Create access token and refresh token plain text
        AuthTokenResponseDto authTokenResponseDto = authTokenUseCase.generateTokensForUser(userDetails.getUsername(), userDetails.getUserId(), rawFingerprint);

        // Save token in cookie
        ResponseCookie jwtCookie = jwtProvider.generateJwtCookie(authTokenResponseDto.accessToken());
        ResponseCookie jwtRefreshCookie = jwtProvider.generateRefreshJwtCookie(authTokenResponseDto.refreshTokenPlain());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(RentXCommonConst.OK_STATUS);
    }
}
