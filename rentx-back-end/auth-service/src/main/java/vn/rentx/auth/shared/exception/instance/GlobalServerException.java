package vn.rentx.auth.shared.exception.instance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.io.Serializable;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GlobalServerException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public GlobalServerException(String message) {
        super(message);
    }

    public GlobalServerException(String message, Throwable cause) {
        super(message, cause);
    }

    public GlobalServerException(Throwable cause) {
        super(cause);
    }

    public GlobalServerException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
