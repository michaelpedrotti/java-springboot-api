package xyz.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import jakarta.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class Http {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> notFound(){

        return ResponseEntity.notFound().build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> badRequest(MethodArgumentNotValidException ex){

        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> errors = ex.getFieldErrors();

        errors.stream().map((FieldError error) -> map.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(map);
        // return ResponseEntity.badRequest().build();
    }
}