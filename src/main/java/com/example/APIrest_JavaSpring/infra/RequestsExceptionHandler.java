package com.example.APIrest_JavaSpring.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity <String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.badRequest().body("Erro: " + ex.getMessage());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity <String> handleIllegalArgumentException(IllegalArgumentException ex){
        return ResponseEntity.badRequest().body("Erro: " + ex.getMessage());
    }
}
