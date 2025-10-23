package vn.rentx.auth.domain.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.rentx.auth.domain.user.shared.ValueObject;

import java.util.regex.Pattern;

@Getter
@Setter
@ToString
public final class Email extends ValueObject<Email> {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private final String value;

    public Email(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be blank");
        }
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.value = value.toLowerCase();
    }

    @Override
    protected boolean equalsCore(Email other) {
        return this.value.equals(other.value);
    }

    @Override
    protected int hashCodeCore() {
        return 0;
    }

}
