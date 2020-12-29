package pl.kurs.java.impl;

import org.springframework.stereotype.Component;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.model.Methoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Dates implements Methoder<List<String>> {

    private List<String> dates(List<String> stringList, String format){
        DateTimeFormatter dateTimeFormatter;
        try{
            dateTimeFormatter = DateTimeFormatter.ofPattern(Optional.ofNullable(format).orElse(""));
        }catch (IllegalArgumentException e){
            dateTimeFormatter = DateTimeFormatter.ofPattern("");
        }

        DateTimeFormatter finalDateTimeFormatter = dateTimeFormatter;
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> {
                    try {
                        LocalDate.parse(s, finalDateTimeFormatter);
                        return true;
                    }catch (DateTimeParseException e){
                        return false;
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> performAction(ActionOnWords actionOnWords) {
        return dates(actionOnWords.getWords(), Methoder.getStringArg(0, Methoder.getParameters(actionOnWords)));
    }
}
