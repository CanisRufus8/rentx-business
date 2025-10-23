package vn.rentx.auth.domain.user.exception;

import java.io.Serial;

public class UsernameExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UsernameExistsException(String message) {
        super(message);
    }

}