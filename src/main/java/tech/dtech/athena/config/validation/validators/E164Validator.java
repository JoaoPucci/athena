package tech.dtech.athena.config.validation.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import tech.dtech.athena.config.validation.validators.annotations.E164;

public class E164Validator implements ConstraintValidator<E164, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null ? Pattern.compile("^\\+?[1-9]\\d{1,14}$").matcher(value).matches() : true;
    }

}
