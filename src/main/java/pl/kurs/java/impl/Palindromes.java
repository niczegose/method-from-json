package pl.kurs.java.impl;

import org.springframework.stereotype.Component;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.Methoder;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Palindromes implements Methoder<List<String>> {

    private List<String> palindromes(List<String> stringList){
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(this::isPalindrome)
                .collect(Collectors.toList());
    }

    private boolean isPalindrome(String word){
        boolean isSame = true;
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }

    @Override
    public List<String> performAction(ActionOnWords actionOnWords) {
        return palindromes(actionOnWords.getWords());
    }
}
