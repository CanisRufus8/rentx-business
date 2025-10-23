package vn.rentx.auth.shared.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.rentx.auth.shared.constant.HttpStatusCodeConts;
import vn.rentx.auth.shared.exception.content.ErrorModel;

import java.time.Instant;

@RestControllerAdvice
public class GlobalServerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModel> handleGlobalException(Exception e) {

        ErrorModel errorMessage = new ErrorModel(
                HttpStatusCodeConts.SERVER_ERROR,
                e.getMessage(),
                Instant.now());

        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

}
