package com.zagalabs.tasklist.utils.validations;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidatorImplementation implements ConstraintValidator<DateFormatValidator, String> {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public boolean isValid(String date, ConstraintValidatorContext constraintValidatorContext) {
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        if (StringUtils.isBlank(date))
            return false;

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
