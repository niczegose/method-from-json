package pl.kurs.java.impl;

import org.springframework.stereotype.Component;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.Methoder;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Anagrams implements Methoder<List<String>> {

    private List<String> anagrams(List<String> stringList, String baseWord){
        String pattern = sort(Optional.ofNullable(baseWord).orElse(""));
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(w -> pattern.equals(sort(w)))
                .collect(Collectors.toList());
    }
    private String sort(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Override
    public List<String> performAction(ActionOnWords actionOnWords) {
        return anagrams(actionOnWords.getWords(), Methoder.getStringArg(0, Methoder.getParameters(actionOnWords)));
    }
}
