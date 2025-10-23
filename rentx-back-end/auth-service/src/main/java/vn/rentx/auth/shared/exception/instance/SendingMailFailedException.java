package vn.rentx.auth.shared.exception.instance;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class SendingMailFailedException extends RuntimeException {

    public SendingMailFailedException(String message) {
        super(message);
    }

}
