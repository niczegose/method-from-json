package pl.kurs.java.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Data
public class MethodIngredients {

    private String methodName;
    private List<String> parameters;

    private MethodIngredients(String methodName, List<String> parameters) {
        this.methodName = methodName;
        this.parameters = parameters;
    }

    public static MethodIngredients createFromAction(String action){
        Pattern pattern = Pattern.compile("([^(]*)\\(([^)]*)\\)");
        Matcher matcher = pattern.matcher(action);
        String methodName="";
        List<String> params = new ArrayList<>();
        if (matcher.matches()){
            methodName = matcher.group(1).toLowerCase();
            params = parametersListCreate(matcher.group(2));
        }
        return new MethodIngredients(methodName, params);
    }

    private static List<String> parametersListCreate(String params){
        return Arrays.stream(params.split(","))
                .map(String::trim)
                .filter(s -> s.matches(".+"))
                .collect(Collectors.toList());
    }

}
