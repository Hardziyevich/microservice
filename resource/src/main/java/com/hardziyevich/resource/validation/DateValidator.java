package com.hardziyevich.resource.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class DateValidator implements ConstraintValidator<DateValidation, String> {

    @Override
    public boolean isValid(String day, ConstraintValidatorContext context) {
        try {
            Optional.ofNullable(day).ifPresent(LocalDate::parse);
        } catch (DateTimeParseException e) {
            return day.isBlank();
        }
        return true;
    }
}
