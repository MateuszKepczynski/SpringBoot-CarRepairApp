package com.springapp.carrepairshop.carrepairshop.rest.handler.owner;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class OwnerExceptionHandler extends ResponseEntityExceptionHandler
{
    public OwnerExceptionHandler()
    {
    }

    @ExceptionHandler(value = {OwnerNotFoundException.class , NoSuchElementException.class})
    public ResponseEntity handleOwnerNotFoundException(RuntimeException e, WebRequest req)
    {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
