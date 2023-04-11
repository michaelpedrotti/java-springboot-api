package xyz.api.responses.bodies;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class BodyToken implements Serializable {

    public String token;
}