package com.zl.gateway.util;

import com.zl.gateway.cache.DefaultCacheReader;
import com.zl.gateway.cache.RedisCacheContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author zhangliang
 * @date 2019/11/20.
 */
@Component
public class RedisUtil<T>{

    @Autowired
    private RedisCacheContainer redisCacheContainer;

    public T read(Function<?, String> getCacheKey, Function<?, T> getData, int expiredSeconds, Class<T> clazz) {
        return new DefaultCacheReader<T>(redisCacheContainer,getCacheKey,getData,expiredSeconds).read(clazz);
    }

    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.read(x ->"",x ->{
           return   "";
        },100,Integer.class);
    }
}
