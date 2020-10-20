package tech.dtech.athena.config.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import tech.dtech.athena.config.validation.validators.annotations.E164;

public class E164Validator implements ConstraintValidator<E164, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || Pattern.compile("^\\+(?:[0-9]?){6,14}[0-9]$").matcher(value).matches();
    }

}
