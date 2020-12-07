package pl.kurs.java.controller.error.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

import java.util.List;

@Value
public class ValidatorErrorDto {

    public List<ErrorDto> errors;

    @Data
    @AllArgsConstructor
    public static class ErrorDto {
        private String property;
        private String message;
    }
}
