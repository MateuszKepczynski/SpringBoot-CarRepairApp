package com.springapp.carrepairshop.carrepairshop.rest.handler.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler
{
    public UserExceptionHandler()
    {
    }

    @ExceptionHandler(value = {UserNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity handleUserNotFoundException(RuntimeException e, WebRequest req)
    {
        return  new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
