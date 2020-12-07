package pl.kurs.java.validation.annotation;

import pl.kurs.java.validation.annotation.impl.ValidateActionValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateActionValidator.class)
@Target( {ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAction {
    String message() default "Action not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
