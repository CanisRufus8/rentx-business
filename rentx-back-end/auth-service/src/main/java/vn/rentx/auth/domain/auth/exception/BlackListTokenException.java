package vn.rentx.auth.domain.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class BlackListTokenException extends RuntimeException{
    public BlackListTokenException(String message) {
        super(message);
    }
}
