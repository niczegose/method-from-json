package pl.kurs.java.model;

import lombok.Data;
import pl.kurs.java.validation.annotation.ValidateAction;

import java.util.List;

@ValidateAction
@Data
public class ActionOnWords {
    private List<String> words;
    private String action;
}
