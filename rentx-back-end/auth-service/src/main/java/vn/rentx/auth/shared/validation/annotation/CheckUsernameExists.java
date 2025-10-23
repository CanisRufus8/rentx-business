package vn.rentx.auth.shared.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.rentx.auth.shared.validation.validator.UsernameExistsValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUsernameExists {

    String message() default "Username is invalid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
