package xyz.api.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import xyz.api.responses.bodies.BaseBody;
import xyz.api.responses.bodies.InterfaceBody;
import xyz.api.responses.bodies.ValidatedBody;

@RestControllerAdvice
public class Http {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<InterfaceBody> notFound(){

        var body = new BaseBody();
        body.setError(true);
        body.setMessage("Record was not found");

        return ResponseEntity.status(404).body(body);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InterfaceBody> badRequest(MethodArgumentNotValidException ex){

        var body = new ValidatedBody();
        body.setError(true);
        body.setMessage("Check form fields");
        body.setFields(ex);

        return ResponseEntity.status(400).body(body);
    }
}