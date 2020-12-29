package pl.kurs.java.impl;

import org.springframework.stereotype.Component;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.Methoder;

import java.util.*;

@Component
public class Longest implements Methoder<String> {

    private String longest(List<String> stringList){
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    @Override
    public String performAction(ActionOnWords actionOnWords) {
        return longest(actionOnWords.getWords());
    }
}
