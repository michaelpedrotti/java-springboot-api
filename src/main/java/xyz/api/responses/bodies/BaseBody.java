package xyz.api.responses.bodies;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseBody implements Serializable, InterfaceBody {

    public Boolean error = false;

    @JsonInclude(Include.NON_EMPTY)
    public String message;
}