package xyz.api.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
    
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    public LoginRequest(String email, String password){

        this.email = email;
        this.password = password;
    }
}
