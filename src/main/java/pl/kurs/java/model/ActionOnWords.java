package pl.kurs.java.model;

import lombok.Data;

import java.util.List;

@Data
public class ActionOnWords {
    private List<String> words;
    private String action;
}
