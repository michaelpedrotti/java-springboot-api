package xyz.api.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import xyz.api.responses.ResponseJSON;

@RestControllerAdvice
public class Http {

    @Autowired
    private ResponseJSON response;
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseJSON> notFound(){

        return response.fail("Not found", 404).build();
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseJSON> badRequest(MethodArgumentNotValidException ex){

        return response.badRequest("Check form fields", ex).build();
    }
}