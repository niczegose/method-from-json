package pl.kurs.java.impl;

import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MethodProvider {

    public static List<String> palindromes(List<String> stringList){
        return stringList;
    }

    public static List<String> length(List<String> stringList, String rootLength){
        return stringList;
    }

    public static List<String> anagrams(List<String> stringList, String baseWord){
        return stringList;
    }

    public static List<String> longest(List<String> stringList){
        return Collections.singletonList(stringList.stream()
                .filter(Objects::nonNull)
                .max(String::compareToIgnoreCase)
                .orElse(""));
    }

    public static List<String> dates(List<String> stringList, String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        List<LocalDate> localDates = stringList.stream()
                .filter(Objects::nonNull)
                .map(s -> {
                    LocalDate localDate;
                    try {
                        localDate = LocalDate.parse(s, dateTimeFormatter);
                    }catch (DateTimeParseException e){
                        localDate = null;
                    }
                    return localDate;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


        return localDates.stream()
                .map(LocalDate::toString)
                .collect(Collectors.toList());
    }
}
