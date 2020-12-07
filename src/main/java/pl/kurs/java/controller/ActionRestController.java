package pl.kurs.java.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.service.MethodService;

import javax.validation.Valid;

@RestController
@RequestMapping("/action")
public class ActionRestController {

    @GetMapping("/")
    public ResponseEntity<Object> performActionOnWords(@RequestBody @Valid ActionOnWords actionOnWords) {
        MethodService methodService = new MethodService(actionOnWords);
        return new ResponseEntity<>(methodService.createResponse(), HttpStatus.ACCEPTED);
    }
}
