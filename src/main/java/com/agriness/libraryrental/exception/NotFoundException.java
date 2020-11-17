package com.agriness.libraryrental.exception;

import com.agriness.libraryrental.config.ObjectUtils;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpException {

    private final SimpleError error;

    public NotFoundException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
        this.error = new SimpleError(errorMessage);
    }

    @Override
    public SimpleError getError() {
        return error;
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }
}
