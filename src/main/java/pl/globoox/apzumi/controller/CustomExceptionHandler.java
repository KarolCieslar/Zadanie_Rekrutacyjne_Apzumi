package pl.globoox.apzumi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.globoox.apzumi.exception.ExceptionResponse;
import pl.globoox.apzumi.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode("NOT_FOUND");
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
    }
}