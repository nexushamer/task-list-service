package com.zagalabs.tasklist.utils.validations;

import com.zagalabs.tasklist.enums.TaskStates;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StateValidatorImplementation implements ConstraintValidator<StateValidator, String> {
    @Override
    public boolean isValid(String state, ConstraintValidatorContext constraintValidatorContext) {
        return TaskStates.DONE.getDescription().equals(state) || TaskStates.PENDING.getDescription().equals(state);
    }
}
