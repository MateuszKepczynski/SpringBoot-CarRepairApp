package com.springapp.carrepairshop.carrepairshop.rest.handler.vehicle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class VehicleExceptionHandler extends ResponseEntityExceptionHandler
{
    public VehicleExceptionHandler()
    {
    }

    @ExceptionHandler(value = {VehicleNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity handleVehicleNotFoundException(RuntimeException e, WebRequest req)
    {
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
