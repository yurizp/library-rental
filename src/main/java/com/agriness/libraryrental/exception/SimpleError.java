package com.agriness.libraryrental.exception;

import com.agriness.libraryrental.config.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleError {

    private String message;
    private String code;

    public SimpleError(ErrorMessage errorMessage) {
        this.message = errorMessage.getErrorMessage();
        this.code = errorMessage.getCode();
    }

    @SneakyThrows
    @Override
    public String toString() {
        return ObjectUtils.toString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleError)) return false;
        SimpleError that = (SimpleError) o;
        return Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessage(), getCode());
    }
}
