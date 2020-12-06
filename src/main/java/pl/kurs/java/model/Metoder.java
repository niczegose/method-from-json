package pl.kurs.java.model;

import java.util.List;

@FunctionalInterface
public interface Metoder {
    List<String> performAction(List<String> stringList);
}
