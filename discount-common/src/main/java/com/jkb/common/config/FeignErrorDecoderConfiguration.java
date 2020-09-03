package com.jkb.common.config;

import cn.hutool.json.JSONUtil;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.jkb.common.base.ResultObject;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * feign全局异常处理, 当400,404，500时会走这个，因为httpStatus都是200所以需要在feign调用成功之后再判断Header里是否是500异常（FeignInterceptorImpl.doAfter()方法）
 */
@Slf4j
@Configuration
public class FeignErrorDecoderConfiguration implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            log.error(methodKey+"，feign httpStatus：" + response.status());
            String message = JSONUtil.toBean(Util.toString(response.body().asReader()), ResultObject.class).getMsg();
            return new HystrixBadRequestException(message);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new Default().decode(methodKey, response);
    }
}