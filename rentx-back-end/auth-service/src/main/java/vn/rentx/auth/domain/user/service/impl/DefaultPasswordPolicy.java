package vn.rentx.auth.domain.user.service.impl;

import org.springframework.util.StringUtils;
import vn.rentx.auth.domain.user.service.PasswordPolicy;

import java.util.regex.Pattern;

public class DefaultPasswordPolicy implements PasswordPolicy {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    /**
     * Password must satisfy:
     * - Minimum 8 characters
     * - At least 1 uppercase letter
     * - At least 1 lowercase letter
     * - At least 1 number
     * - At least 1 special character
     */
    @Override
    public boolean isSatisfiedBy(String password) {
        if (StringUtils.hasLength(password)) {
            return PASSWORD_PATTERN.matcher(password).matches();
        }
        return false;
    }

    @Override
    public boolean isStrong(String password) {
        return true;
    }

}
