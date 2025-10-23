package vn.rentx.auth.shared.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.rentx.auth.domain.user.exception.UserNotFoundException;
import vn.rentx.auth.shared.constant.HttpStatusCodeConts;
import vn.rentx.auth.shared.exception.content.ErrorModel;

import java.time.Instant;

@RestControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ErrorModel> handleUserNotFoundException(UserNotFoundException e) {

        ErrorModel errorMessage = new ErrorModel(
                HttpStatusCodeConts.NOT_FOUND,
                e.getMessage(),
                Instant.now());

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

}
