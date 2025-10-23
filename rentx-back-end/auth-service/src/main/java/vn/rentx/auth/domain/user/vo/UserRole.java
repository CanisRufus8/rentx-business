package vn.rentx.auth.domain.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.rentx.auth.domain.user.shared.ValueObject;

@Getter
@Setter
@ToString
public class UserRole extends ValueObject<UserRole> {

    private String value;

    public UserRole(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("User Role must not be blank");
        }
        this.value = value;
    }

    @Override
    protected boolean equalsCore(UserRole other) {
        return this.value.equals(other.value);
    }

    @Override
    protected int hashCodeCore() {
        return 0;
    }
}
