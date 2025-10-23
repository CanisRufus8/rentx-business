package vn.rentx.auth.domain.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExpiredAccessTokenException extends RuntimeException {
    public ExpiredAccessTokenException(String message) {
        super(message);
    }
}
