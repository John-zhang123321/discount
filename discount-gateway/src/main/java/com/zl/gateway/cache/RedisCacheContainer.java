package com.zl.gateway.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangliang
 * @date 2019/11/20.
 */
@Component
public class RedisCacheContainer implements CacheContainer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, ?> redisTemplate;

    @Override
    public Object get(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void add(String key, Object value, long expiredSeconds) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        ops.set(key, String.valueOf(value), expiredSeconds, TimeUnit.SECONDS);
    }

    @Override
    public void delete(String key) {
        this.stringRedisTemplate.delete(key);
    }
    @Override
    public void removeKey(String key) {
        Set<String> keys = redisTemplate.keys(key);
        this.redisTemplate.delete(keys);
    }
    public void setKeyExpire(String key, int expiredSeconds) {
        //设置过期时间
        stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
    }

}
