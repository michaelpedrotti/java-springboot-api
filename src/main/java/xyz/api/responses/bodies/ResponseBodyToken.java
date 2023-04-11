package xyz.api.responses.bodies;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class ResponseBodyToken implements Serializable {

    public String token;
}