package com.agriness.libraryrental.exception;

import org.springframework.http.HttpStatus;

public abstract class HttpException extends RuntimeException {

    protected HttpException(String message) {
        super(message);
    }

    public abstract SimpleError getError();

    public abstract HttpStatus getStatus();
}
