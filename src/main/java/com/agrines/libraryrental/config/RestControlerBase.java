package com.agrines.libraryrental.config;


import com.agrines.libraryrental.exception.HttpException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControlerBase {

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<Object> handleNotFound(final HttpException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getError());
    }

}

