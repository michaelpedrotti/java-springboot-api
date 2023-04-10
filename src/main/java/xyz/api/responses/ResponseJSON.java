package xyz.api.responses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Component
public class ResponseJSON implements Serializable {
 
    @JsonIgnore
    private Integer status = 200;

    private Boolean error = false;

    @JsonInclude(Include.NON_EMPTY)
    private String message = "";

    @JsonInclude(Include.NON_NULL)
    private Entity data;

    @JsonInclude(Include.NON_NULL)
    private Map<String, String> fields;

    @JsonInclude(Include.NON_NULL)
    private Long total;

    @JsonInclude(Include.NON_NULL)
    private List<Entity> rows;

    public ResponseJSON success(String message){

        return this.success(message, 200);
    }

    public ResponseJSON success(String message, Integer status){

        this.message = message;
        this.status = status;

        return this;
    }

    // public ResponseJSON data(Object data){

    //     this.data = data;

    //     return this;
    // }

    public ResponseJSON fail(String message){

        return this.fail(message, 500);
    }  

    public ResponseJSON fail(String message, Integer status){

        this.message = message;
        this.status = status;
        return this;
    }

    public ResponseJSON badRequest(String message, MethodArgumentNotValidException ex){

        this.status = 400;
        this.message = message;
        this.fields = new HashMap<String, String>();

        ex.getFieldErrors()
            .stream()
                .map((FieldError error) -> this.fields.put(error.getField(), error.getDefaultMessage()));

        return this;
    }

    public ResponseEntity<ResponseJSON> build(){

        return ResponseEntity.status(this.status).body(this);
    }
}