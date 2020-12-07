package pl.kurs.java.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class MethodProvider {

    public static List<String> palindromes(List<String> stringList){
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(MethodProvider::isPalindrome)
                .collect(Collectors.toList());
    }

    private static boolean isPalindrome(String word){
        boolean isSame = true;
        for (int i = 0; i < word.length() / 2; i++) {
            if (word.charAt(i) != word.charAt(word.length() - 1 - i)) {
                isSame = false;
                break;
            }
        }
        return isSame;
    }

    public static List<String> length(List<String> stringList, int baseLength){
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(s -> s.length()==baseLength)
                .collect(Collectors.toList());
    }

    public static List<String> anagrams(List<String> stringList, String baseWord){
        String pattern = sort(Optional.ofNullable(baseWord).orElse(""));
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .filter(w -> pattern.equals(sort(w)))
                .collect(Collectors.toList());
    }
    private static String sort(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static String longest(List<String> stringList){
        return Optional.ofNullable(stringList)
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .max(Comparator.comparing(String::length))
                .orElse("");
    }

    public static List<String> dates(List<String> stringList, String format){
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
}
