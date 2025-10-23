package vn.rentx.auth.shared.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vn.rentx.auth.application.port.out.UserRepositoryPort;
import vn.rentx.auth.shared.validation.annotation.CheckUsernameExists;

@RequiredArgsConstructor
public class UsernameExistsValidator implements ConstraintValidator<CheckUsernameExists, String> {

    private final UserRepositoryPort userRepositoryPort;
    private static final String USERNAME = "username";

    @Override
    public void initialize(CheckUsernameExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (userRepositoryPort.existsByUsername(value)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    String.format("Username %s is already in use.", value)
            ).addConstraintViolation();

            return false;
        }

        return !value.equals(USERNAME);
    }

}
