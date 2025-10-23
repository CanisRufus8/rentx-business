package vn.rentx.auth.domain.user.event;

import lombok.AllArgsConstructor;
import vn.rentx.auth.domain.base.EventBase;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class UserRegisteredEvent extends EventBase {

    private final UUID userId;
    private final String username;
    private final String email;
    private final LocalDateTime occurredAt;

}
