package org.abhinavgpt.commenz.exceptionhander;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.exceptions.URLNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidURLException.class)
    public ResponseEntity<String> handleInvalidURL(InvalidURLException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(URLNotSupportedException.class)
    public ResponseEntity<String> handleURLNotSupported(URLNotSupportedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}