package ${package}.domain.validation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ${package}.domain.validation.annotations.IsMajeur;

public class DateValidator implements ConstraintValidator<IsMajeur, LocalDate> {

    protected LocalDate birthDate;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if(value == null)
            return false;
        LocalDate today = LocalDate.now();
        return ChronoUnit.YEARS.between(value, today) >= 18;
    }
    
}
