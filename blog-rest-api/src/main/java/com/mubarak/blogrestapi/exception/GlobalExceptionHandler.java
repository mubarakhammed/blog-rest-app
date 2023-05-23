package com.mubarak.blogrestapi.exception;

import com.mubarak.blogrestapi.payload.Errordetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

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


}
