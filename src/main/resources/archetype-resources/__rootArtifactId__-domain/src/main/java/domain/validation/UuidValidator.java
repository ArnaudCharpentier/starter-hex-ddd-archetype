package ${package}.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ${package}.domain.validation.annotations.ValidUuid;

public class UuidValidator implements ConstraintValidator<ValidUuid, String> {

    private final String regex = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}";
    protected String uuid;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null)
            return false;
        return value.matches(regex);
    }
    
}