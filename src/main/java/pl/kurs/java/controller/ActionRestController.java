package pl.kurs.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kurs.java.impl.MethodProvider;
import pl.kurs.java.model.ActionOnWords;
import pl.kurs.java.service.MethodService;

import javax.validation.Valid;

@RestController
@RequestMapping("/action")
public class ActionRestController {

    @Autowired
    MethodService methodService;

    @GetMapping("/")
    public ResponseEntity<Object> performActionOnWords(@RequestBody @Valid ActionOnWords actionOnWords) {
        return new ResponseEntity<>(methodService.createResponse(actionOnWords), HttpStatus.ACCEPTED);
    }
}
