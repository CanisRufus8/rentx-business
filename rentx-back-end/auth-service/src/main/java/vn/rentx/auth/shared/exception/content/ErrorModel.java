package vn.rentx.auth.shared.exception.content;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private Instant errorTime;

}
