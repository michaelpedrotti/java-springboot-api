package xyz.api.controllers;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import xyz.api.entities.AuthEntity;
import xyz.api.requests.LoginRequest;
import xyz.api.responses.ResponseJSON;
import xyz.api.responses.bodies.BodyToken;
import xyz.api.services.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private TokenService token;

    @PostMapping("/login")
    public ResponseEntity<ResponseJSON> login(@RequestBody @Valid LoginRequest request,  @Autowired ResponseJSON response){

        var authentication = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var authenticate = this.manager.authenticate(authentication);
        String token = this.token.generate((AuthEntity)authenticate.getPrincipal());

        var data = new BodyToken(token);

        return response.data(data).build();
    }
}
