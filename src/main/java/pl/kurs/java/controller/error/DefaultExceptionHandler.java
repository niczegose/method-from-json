package pl.kurs.java.controller.error;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.kurs.java.controller.error.model.ValidatorErrorDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.kurs.java.controller.error.model.ValidatorErrorDto.*;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidatorErrorDto errors = new ValidatorErrorDto(
                createErrorList(ex)
        );
        return ResponseEntity.badRequest().body(errors);
    }

    private List<ErrorDto> createErrorList( MethodArgumentNotValidException ex){
        List<ErrorDto> errorDto = new ArrayList<>();
        errorDto.addAll(ex.getBindingResult().getGlobalErrors().stream().map(e -> new ErrorDto(e.getObjectName(), e.getDefaultMessage())).collect(Collectors.toList()));
        errorDto.addAll(ex.getBindingResult().getFieldErrors().stream().map(e -> new ErrorDto(e.getField(), e.getDefaultMessage())).collect(Collectors.toList()));
        return errorDto;
    }

}
