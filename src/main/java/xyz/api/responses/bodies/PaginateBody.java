package xyz.api.responses.bodies;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaginateBody extends BaseBody {
    
    @JsonInclude(Include.NON_EMPTY)
    public Long total;


    @JsonInclude(Include.NON_NULL)
    public List<?> rows = null;
}
