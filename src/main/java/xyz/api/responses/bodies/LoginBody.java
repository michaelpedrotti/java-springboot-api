package xyz.api.responses.bodies;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class LoginBody extends BaseBody {

    public String token;
}