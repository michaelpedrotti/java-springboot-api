package xyz.api.responses;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import xyz.api.responses.bodies.LoginBody;

@Getter
@Component
@Deprecated
public class ResponseJSON implements Serializable {
 
    @JsonIgnore
    private Integer status = 200;

    private Boolean error = false;

    @JsonInclude(Include.NON_EMPTY)
    private String message = "";

    @JsonInclude(Include.NON_NULL)
    private Serializable data = null;

    @JsonInclude(Include.NON_NULL)
    private Serializable form = null;

    @JsonInclude(Include.NON_NULL)
    private Map<String, String> fields = null;

    @JsonInclude(Include.NON_NULL)
    private Long total;

    @JsonInclude(Include.NON_NULL)
    private List<?> rows = null;

    public ResponseJSON success(){

        return this.success("", 200);
    }

    public ResponseJSON success(String message){

        return this.success(message, 200);
    }

    public ResponseJSON success(String message, Integer status){

        this.message = message;
        this.status = status;
        return this;
    }

    public ResponseJSON page(Page<?> page){

        this.total = page.getTotalElements();
        this.rows = page.getContent();
        return this;
    }

    public ResponseJSON form(Serializable form){

        this.form = form;
        return this;
    }

    public ResponseJSON token(String token){

        this.data = new LoginBody(token);
        return this;
    }

    public ResponseJSON data(Serializable data){

        this.data = data;
        return this;
    }

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

    public ResponseJSON notFound(String message){

        return this.success(message, 404);
    }   

    public ResponseEntity<ResponseJSON> build(){

        return ResponseEntity.status(this.status).body(this);
    }

    static public ResponseJSON create(){
        return new ResponseJSON();
    }
}