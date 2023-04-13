package xyz.api.responses.bodies;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ValidatedBody extends BaseBody {
    
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> fields = null;

    public ValidatedBody setFields(MethodArgumentNotValidException ex){

        this.fields = new HashMap<String, String>();

        ex.getFieldErrors()
            .stream()
                .map((FieldError error) -> this.fields.put(error.getField(), error.getDefaultMessage()));

        return this;
    }
}
