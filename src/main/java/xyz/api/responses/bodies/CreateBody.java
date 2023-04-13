package xyz.api.responses.bodies;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBody extends BaseBody {

    public Serializable form;
}