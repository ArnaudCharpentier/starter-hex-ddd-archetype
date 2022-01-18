package ${package}.domain.validation.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ${package}.domain.validation.UuidValidator;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UuidValidator.class})
@Documented
public @interface ValidUuid {
    String message() default "L'identifiant unique n'est pas un UUID valide'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
