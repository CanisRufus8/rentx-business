package vn.rentx.auth.shared.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import vn.rentx.auth.application.port.out.UserRepositoryPort;
import vn.rentx.auth.shared.validation.annotation.CheckEmailExists;

@RequiredArgsConstructor
public class EmailExistsValidator implements ConstraintValidator<CheckEmailExists, String> {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void initialize(CheckEmailExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (userRepositoryPort.existsByEmail(value)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    String.format("Email" +
                            "" +
                            "" +
                            " %s is already in use.", value)
            ).addConstraintViolation();
            ;
            return false;
        }

        return true;
    }

}
