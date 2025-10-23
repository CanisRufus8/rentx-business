package vn.rentx.auth.domain.user.exception;

import java.io.Serial;

public class EmailExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailExistsException(String message) {
        super(message);
    }
}
