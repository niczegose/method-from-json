package pl.kurs.java.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.service.MethodService;

import javax.validation.Valid;

@RestController
@RequestMapping("/action")
@RequiredArgsConstructor
public class ActionRestController {

    private final MethodService methodService;

    @GetMapping("/")
    public ResponseEntity<Object> performActionOnWords(@RequestBody @Valid ActionOnWords actionOnWords) {
        return new ResponseEntity<>(methodService.createResponse(actionOnWords), HttpStatus.ACCEPTED);
    }
}
