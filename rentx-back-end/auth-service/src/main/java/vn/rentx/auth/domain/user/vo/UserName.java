package vn.rentx.auth.domain.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.rentx.auth.domain.user.shared.ValueObject;

@Getter
@Setter
@ToString
public final class UserName extends ValueObject<UserName> {

    private final String value;

    public UserName(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username must not be blank");
        }
        if (value.length() < 4 || value.length() > 32) {
            throw new IllegalArgumentException("Username must be between 4 and 32 characters");
        }
        if (!value.matches("^[a-zA-Z0-9_.-]+$")) {
            throw new IllegalArgumentException("Username contains invalid characters");
        }
        this.value = value;
    }

    @Override
    protected boolean equalsCore(UserName other) {
        return this.value.equals(other.value);
    }

    @Override
    protected int hashCodeCore() {
        return 0;
    }

}