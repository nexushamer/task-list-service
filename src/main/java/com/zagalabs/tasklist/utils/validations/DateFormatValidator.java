package com.zagalabs.tasklist.utils.validations;

import com.zagalabs.tasklist.utils.ConstantMessages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidatorImplementation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatValidator {
    String message() default ConstantMessages.DATE_OF_COMPLETION_IS_INVALID;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
