package com.zagalabs.tasklist.utils.validations;

 import javax.validation.Constraint;
 import javax.validation.Payload;
 import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StateValidatorImplementation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StateValidator {
    String message() default "The state is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
