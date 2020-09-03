package com.jkb.common.cache;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
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

    public <T> T getObject(String key, Class<T> clazz) {
        try{
            String jsonStr = this.stringRedisTemplate.opsForValue().get(key);
            if (null == jsonStr) {
                return null;
            }
            return JSONObject.parseObject(jsonStr, clazz);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void remove(String key) {
        this.stringRedisTemplate.delete(key);
    }
    @Override
    public void removeKey(String key) {
        Set<String> keys = redisTemplate.keys(key);
        this.redisTemplate.delete(keys);
    }
    public void setExpire(String key, int expiredSeconds) {
        //设置过期时间
        stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
    }

    /**
     * 向队列中添加消息
     * 存储REDIS队列 顺序存储
     *
     * @param key
     * @param value
     */
    public long lPush(String key, String value) {

        long result = redisTemplate.execute((RedisCallback<Long>) connection -> {

            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
            return count;
        });
        return result;
    }

    /**
     * 向队列中取出消息
     *
     * @param key
     * @return
     */
    public String lPop(String key) {
        String result = redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] res = connection.lPop(serializer.serialize(key));
            return serializer.deserialize(res);
        });
        return result;
    }

    public Set<String> keys(String keys){
        return redisTemplate.keys(keys);
    }

    /**
     * 向队列中取出消息
     *
     * @param key
     * @return
     */
    public String rPop(String key) {
        String result = redisTemplate.execute((RedisCallback<String>) connection -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] res = connection.rPop(serializer.serialize(key));
            return serializer.deserialize(res);
        });
        return result;
    }

    public List<String> range(String key) {
        ListOperations<String, String> vo = this.stringRedisTemplate.opsForList();
        return vo.range(key, 0, -1);
    }


    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Long incrByInt(String key, long increment) {
        return stringRedisTemplate.opsForValue().increment(key, increment);
    }
    /**
     * 获取剩余的过期时间
     * @return
     */
    public long getLastExpire(String key){
        return this.stringRedisTemplate.getExpire(key,TimeUnit.SECONDS);
    }
}
