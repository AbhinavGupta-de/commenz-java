package org.abhinavgpt.commenz.exceptionhander;

import org.abhinavgpt.commenz.exceptions.InvalidURLException;
import org.abhinavgpt.commenz.exceptions.URLNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidURLException.class, URLNotSupportedException.class})
    public ResponseEntity<String> handleInvalidRequest(InvalidURLException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InvalidURLException.class)
    public ResponseEntity<String> handleInvalidURL(InvalidURLException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(URLNotSupportedException.class)
    public ResponseEntity<String> handleURLNotSupported(URLNotSupportedException e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
//        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}