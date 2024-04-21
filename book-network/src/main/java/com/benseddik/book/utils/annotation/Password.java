package com.benseddik.book.utils.annotation;

import com.benseddik.book.utils.annotation.validator.PasswordConstraintsValidator;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

/**
 * @author Fethi Benseddik
 * @see PasswordConstraintsValidator
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordConstraintsValidator.class)
public @interface Password {
    String message() default "Password should be valid ";

    Class[] groups() default {};

    Class[] payload() default {};
}
