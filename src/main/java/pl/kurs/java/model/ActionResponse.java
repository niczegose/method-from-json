package pl.kurs.java.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ActionResponse<T> {

    private T response;
}
