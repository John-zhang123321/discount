package com.jkb.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangliang on 2020/1/7
 */
@Slf4j
@Configuration
public class FeignTokenInterceptor implements RequestInterceptor{
    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.warn("FeignInterceptorImpl 无法获取到request");
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        String authToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("token [{}]",authToken);
        if (!StringUtils.isBlank(authToken)) {
            template.header(HttpHeaders.AUTHORIZATION, authToken);
        }
    }
}
