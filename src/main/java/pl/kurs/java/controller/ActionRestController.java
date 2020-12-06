package pl.kurs.java.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.java.impl.MethodProvider;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.ActionResponse;
import pl.kurs.java.model.MethodIngredients;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/action")
public class ActionRestController {

    @GetMapping("/")
    public ResponseEntity<Object> performActionOnWords(@RequestBody ActionOnWords actionOnWords) {

        MethodIngredients methodIngredients = MethodIngredients.createFromAction(actionOnWords.getAction());
        Method method;
        List<String> list = new ArrayList<>();
        try {
            if (methodIngredients.getParameters().size() < 1) {
                method = MethodProvider.class.getMethod(methodIngredients.getMethodName(), List.class);
                list = (List<String>) method.invoke(null, actionOnWords.getWords());
            } else {
                List<Class<?>> classes = new ArrayList<>();
                classes.add(List.class);
                for (String str : methodIngredients.getParameters()) {
                    classes.add(String.class);
                }
                method = MethodProvider.class.getMethod(methodIngredients.getMethodName(), classes.toArray(new Class[0]));
                //list = (List<String>) method.invoke(null, actionOnWords.getWords(), methodIngredients.getParameters().toArray(new String[0]));
                list = (List<String>) method.invoke(null, actionOnWords.getWords(), methodIngredients.getParameters().get(0));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        ActionResponse actionResponse = new ActionResponse(String.join(", ", list));

        return new ResponseEntity<>(actionResponse, HttpStatus.ACCEPTED);
    }
}
