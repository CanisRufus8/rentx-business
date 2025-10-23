package vn.rentx.auth.domain.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.rentx.auth.domain.user.shared.ValueObject;

@Getter
@Setter
@ToString
public final class Password extends ValueObject<Password> {

    private final String hashedValue;

    // Constructor for hashed password
    public Password(String hashedValue) {
        if (hashedValue == null || hashedValue.isBlank()) {
            throw new IllegalArgumentException("Password hash must not be blank");
        }
        this.hashedValue = hashedValue;
    }

    @Override
    protected boolean equalsCore(Password other) {
        return this.hashedValue.equals(other.hashedValue);
    }

    @Override
    protected int hashCodeCore() {
        return 0;
    }

}
