package com.jkb.common.utils;

import com.jkb.common.cache.DefaultCacheReader;
import com.jkb.common.cache.RedisCacheContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author zhangliang
 * @date 2019/11/20.
 */
@Component
public class RedisUtil{

    @Autowired
    private RedisCacheContainer redisCacheContainer;

    public <T> T read(Function<?, String> getCacheKey, Function<?, T> getData, int expiredSeconds, Class<T> clazz) {
        return new DefaultCacheReader<T>(redisCacheContainer,getCacheKey,getData,expiredSeconds).read(clazz);
    }

    public void add(String key ,Object value, int expiredSeconds) {
        redisCacheContainer.add(key, value, expiredSeconds);
    }


    public Long incrByInt(String key, long increment) {
        return redisCacheContainer.incrByInt(key, increment);
    }

}
