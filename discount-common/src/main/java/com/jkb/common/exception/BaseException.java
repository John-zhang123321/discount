package com.jkb.common.exception;

import com.jkb.common.constants.RespCodeConstants;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

/**
 * 异常Base类
 */
public class BaseException extends RuntimeException {
    private int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private String message;
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BaseException() {
    }

    public BaseException(String message, int status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BaseException(RespCodeConstants constants) {
        super(constants.getMsg());
        status = constants.getCode();
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
    @Override
    public String toString() {
        return MessageFormat.format("[{0}][{1}]",this.status,this.message);
    }
}
