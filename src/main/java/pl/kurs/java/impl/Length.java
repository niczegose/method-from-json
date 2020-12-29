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
public class Length implements Methoder<List<String>> {

    private List<String> length(List<String> stringList, int baseLength){
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length()==baseLength)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> performAction(ActionOnWords actionOnWords) {
        return length(actionOnWords.getWords(), Methoder.getIntArg(0,Methoder.getParameters(actionOnWords)));
    }
}
