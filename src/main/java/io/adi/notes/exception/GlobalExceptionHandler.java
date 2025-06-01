package io.adi.notes.exception;

import io.adi.notes.dto.HttpResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class  GlobalExceptionHandler {

    @ExceptionHandler(EnumInvalidValuesException.class)
   public ResponseEntity<HttpResponse<?>> EnumInvalidValuesExceptionHandler(Exception ex, HttpServletRequest httpServletRequest){

       HttpResponse<?> httpResponse =  HttpResponse.<HttpResponse<?>>builder()
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .httpStatus(HttpStatus.BAD_REQUEST)
               .data(null)
               .message(ex.getMessage())
               .path(httpServletRequest.getRequestURL().toString())
               .build();

       return new ResponseEntity<>(httpResponse, HttpStatus.BAD_REQUEST);
    }
}
