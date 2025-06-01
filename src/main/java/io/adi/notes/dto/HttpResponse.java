package io.adi.notes.dto;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Collection;

@Data
@Builder
public class HttpResponse<T> implements Serializable {
    private int statusCode;
    private HttpStatus httpStatus;
    private String message;
    private Collection<T> data;
    private String path;
}
