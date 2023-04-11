package xyz.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.api.responses.ResponseJSON;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    
    @Autowired
    private ResponseJSON response;
 
    @GetMapping("/{id}")
    public ResponseEntity<ResponseJSON> show(@PathVariable Long id){

        response.success("Hello world");
        response.fail("Falhou");

        return response.build();  
    }
}