package com.zl.gateway.cache;

/**
 * @author zhangliang
 * @date 2019/11/20.
 */
public interface CacheContainer {
    Object get(String key);

    void add(String key, Object value, long expiredSeconds);

    void delete(String key);

    void removeKey(String key);
}
