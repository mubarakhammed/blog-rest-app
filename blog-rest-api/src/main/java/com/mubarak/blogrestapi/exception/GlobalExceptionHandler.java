package com.mubarak.blogrestapi.exception;

import com.mubarak.blogrestapi.payload.Errordetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //handle specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Errordetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        Errordetails errordetails  = new Errordetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errordetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<Errordetails> handleBlogApiException(BlogApiException exception, WebRequest webRequest){
        Errordetails errordetails  = new Errordetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);
    }





   //handle global exception
   @ExceptionHandler(Exception.class)
   public ResponseEntity<Errordetails> handleGlobalException(Exception exception, WebRequest webRequest){
       Errordetails errordetails  = new Errordetails(new Date(), exception.getMessage(),
               webRequest.getDescription(false));
       return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
   }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName  = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Errordetails> handleAccessDeniedException(AccessDeniedException exception, WebRequest webRequest){
        Errordetails errordetails  = new Errordetails(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errordetails, HttpStatus.UNAUTHORIZED);
    }
}
