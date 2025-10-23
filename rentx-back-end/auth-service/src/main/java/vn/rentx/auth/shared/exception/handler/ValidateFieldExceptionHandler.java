package vn.rentx.auth.shared.exception.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vn.rentx.auth.shared.exception.content.ErrorModel;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ValidateFieldExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException ex) {

        List<ErrorModel> errorModelList = new LinkedList<>();
        List<FieldError> fieldErrorList = ex.getBindingResult().getFieldErrors();
        ErrorModel errorModel;
        Instant now = Instant.now();
        for (FieldError item : fieldErrorList) {
            errorModel = new ErrorModel();
            errorModel.setCode(item.getField());
            errorModel.setMessage(item.getDefaultMessage());
            errorModel.setErrorTime(now);
            errorModelList.add(errorModel);
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorModelList);
    }
}
