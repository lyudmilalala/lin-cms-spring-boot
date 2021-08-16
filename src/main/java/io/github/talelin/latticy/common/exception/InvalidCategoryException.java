package io.github.talelin.latticy.common.exception;

import io.github.talelin.autoconfigure.bean.Code;
import io.github.talelin.autoconfigure.exception.HttpException;
import org.springframework.http.HttpStatus;

public class InvalidCategoryException extends HttpException  {

    private int code = Code.FAIL.getCode();

    private int httpCode = HttpStatus.NOT_ACCEPTABLE.value();

    public InvalidCategoryException(int code) {
        super(code, HttpStatus.NOT_ACCEPTABLE.value());
        this.code = code;
    }

    public InvalidCategoryException(int code, String message) {
        super(code, message, HttpStatus.NOT_ACCEPTABLE.value());
        this.code = code;
    }

}
