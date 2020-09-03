package com.zl.gateway.cache;

import java.util.function.Function;

/**
 * @author zhangliang
 * @date 2019/11/20.
 */
public class DefaultCacheReader<T> extends BaseCacheReader<T> {

    private Function<?, String> getKey;
    private Function<?, T> getData;
    private int expiredSeconds;

    /**
     * 构造函数
     *
     * @param cacheContainer
     * @param getCacheKey
     * @param getData
     * @param expiredSeconds
     */
    public DefaultCacheReader(CacheContainer cacheContainer, Function<?, String> getCacheKey, Function<?, T> getData, int expiredSeconds) {
        super(cacheContainer);
        this.getKey = getCacheKey;
        this.getData = getData;
        this.expiredSeconds = expiredSeconds;
    }

    @Override
    protected String getCacheKey() {
        return getKey.apply(null);
    }

    @Override
    protected T readFromSource() {
        return getData.apply(null);
    }

    @Override
    protected int getExpiredSeconds() {
        return expiredSeconds;
    }
}
