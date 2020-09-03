package com.jkb.common.exception;

import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.jkb.common.base.ResultObject;
import com.jkb.common.constants.RespCodeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.io.UnsupportedEncodingException;

/**
 * 全局异常捕获
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Value("${spring.profiles.active}")
    private String env;
    /**
     * 500- 业务异常
     */
    @ExceptionHandler(BaseException.class)
    public ResultObject baseExceptionHandler(HttpServletResponse response, BaseException ex)  {
        logger.error(ex.getMessage(), ex);
        return new ResultObject(ex.getStatus(), ex.getMessage());
    }

    /**
     * 500- HystrixBadRequestException 熔断异常
     */
    @ExceptionHandler(HystrixBadRequestException.class)
    public ResultObject hystrixBadRequestExceptionHandler(HttpServletResponse response, HystrixBadRequestException ex) {
        logger.error("feign exception：" + ex.getMessage(), ex);
        return new ResultObject(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }


    /**
     * 500- server error
     */
    @ExceptionHandler(Exception.class)
    public ResultObject otherExceptionHandler(HttpServletResponse response, Exception ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        String msg;
        if ("dev".equals(env) || "test".equals(env)) {
            msg = "系统繁忙" + ex.getMessage();
        }else {
            msg = "系统繁忙,请稍后再试";
        }
        return new ResultObject(RespCodeConstants.EX_OTHER_CODE.getCode(), msg);
    }

    /**
     * 缺少请求参数- Bad Request
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultObject handleMissingServletRequestParameterException(HttpServletResponse response, MissingServletRequestParameterException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数解析失败- Bad Request
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultObject handleHttpMessageNotReadableException(HttpServletResponse response, HttpMessageNotReadableException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultObject handleMethodArgumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数绑定失败- Bad Request
     */
    @ExceptionHandler(BindException.class)
    public ResultObject handleBindException(HttpServletResponse response, BindException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultObject handleServiceException(HttpServletResponse response, ConstraintViolationException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 参数验证失败 - Bad Request
     */
    @ExceptionHandler(ValidationException.class)
    public ResultObject handleValidationException(HttpServletResponse response, ValidationException ex) {
        logger.error(ex.getMessage(), ex);
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 400 - 参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultObject processArgumentTypeMismatchException(HttpServletResponse response, MethodArgumentTypeMismatchException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * 415 - 媒体类型不匹配
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResultObject handleHttpMediaTypeNotSupportedException(HttpServletResponse response, HttpMediaTypeNotSupportedException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage());
    }

    /**
     * 405 - 请求方法不匹配
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultObject handleHttpRequestMethodNotSupportedException(HttpServletResponse response, HttpRequestMethodNotSupportedException ex) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResultObject(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage());
    }
}
