package com.agriness.libraryrental.exception;

import com.agriness.libraryrental.config.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpException {

    private SimpleError error;
    private Throwable cause;

    public BadRequestException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.error = new SimpleError(errorMessage);
    }

    @Override
    public SimpleError getError() {
        return error;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }
}
