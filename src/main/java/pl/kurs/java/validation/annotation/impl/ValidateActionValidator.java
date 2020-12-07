package pl.kurs.java.validation.annotation.impl;

import org.springframework.stereotype.Service;
import pl.kurs.java.impl.MethodProvider;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.Methoder;
import pl.kurs.java.validation.annotation.ValidateAction;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidateActionValidator implements ConstraintValidator<ValidateAction, ActionOnWords> {
    @Override
    public boolean isValid(ActionOnWords actionOnWords, ConstraintValidatorContext constraintValidatorContext) {

        List<String> availableMethods = Arrays.stream(MethodProvider.class.getMethods()).map(Method::getName).collect(Collectors.toList());

        return availableMethods.contains(Methoder.getMethodName(actionOnWords));
    }
}
