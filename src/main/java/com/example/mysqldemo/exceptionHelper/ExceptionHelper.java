package com.example.mysqldemo.exceptionHelper;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.logging.Logger;
@RestControllerAdvice
@Slf4j
public class ExceptionHelper {




    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        log.error("Invalid Input Exception: ",ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { HttpClientErrorException.Unauthorized.class })

    public ResponseEntity<Object> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {

        log.error("Unauthorized Exception: ",ex.getMessage());

        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(value = { Exception.class })

    public ResponseEntity<Object> handleException(Exception ex) {

        log.error("Exception: ",ex.getMessage());

        return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
