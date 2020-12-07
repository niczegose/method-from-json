package pl.kurs.java.model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@FunctionalInterface
public interface Methoder<T> {
    T performAction(List<String> stringList);

    static List<String> getParameters(ActionOnWords actionOnWords) {
        Matcher matcher = getMatcher(actionOnWords);
        List<String> params = new ArrayList<>();
        if (matcher.matches()) {
            params = parametersListCreate(matcher.group(2));
        }
        return params;
    }

    private static List<String> parametersListCreate(String params) {
        return Arrays.stream(params.split(","))
                .map(String::trim)
                .filter(s -> s.matches(".+"))
                .collect(Collectors.toList());
    }

    static String getMethodName(ActionOnWords actionOnWords) {
        Matcher matcher = getMatcher(actionOnWords);
        String methodName = "";
        if (matcher.matches()) {
            methodName = matcher.group(1).toLowerCase();
        }
        return methodName;
    }

    @NotNull
    private static Matcher getMatcher(ActionOnWords actionOnWords) {
        Pattern pattern = Pattern.compile("([^(]*)\\(([^)]*)\\)");
        return pattern.matcher(actionOnWords.getAction());
    }

    static String getStringArg(int index, List<String> args) {
        return Optional.ofNullable(args)
                .filter(m -> m.size() >= index)
                .orElseThrow(IllegalArgumentException::new)
                .get(index);
    }

    static int getIntArg(int index, List<String> args) {
        return Optional.of(Optional.ofNullable(args)
                .filter(m -> m.size() >= index)
                .orElseThrow(IllegalArgumentException::new)
                .get(index))
                .map(Integer::parseInt)
                .orElseThrow(NumberFormatException::new);
    }

}
