package vn.rentx.auth.shared.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import vn.rentx.auth.shared.validation.validator.EmailExistsValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEmailExists {

    String message() default "Email exists, please try again.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
