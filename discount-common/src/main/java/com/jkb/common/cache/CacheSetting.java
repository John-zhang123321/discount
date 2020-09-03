package com.jkb.common.cache;

/**
 * @author zhangliang
 * @date 2019/11/20.
 */
public class CacheSetting {

    /**
     * 缓存Key
     */
    private String key;
    /**
     * 缓存时间，单位（秒）
     */
    private int expiredSeconds;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getExpiredSeconds() {
        return expiredSeconds;
    }

    public void setExpiredSeconds(int expiredSeconds) {
        this.expiredSeconds = expiredSeconds;
    }
}
