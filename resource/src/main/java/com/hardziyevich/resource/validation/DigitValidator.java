package com.hardziyevich.resource.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class DigitValidator implements ConstraintValidator<DigitValidation, String> {

    private static final String REG_DIGIT = "\\d*";

    @Override
    public boolean isValid(String digit, ConstraintValidatorContext context) {
        Optional<String> optional = Optional.ofNullable(digit);
        return optional.isEmpty() ? false : optional.get().matches(REG_DIGIT);
    }

}
