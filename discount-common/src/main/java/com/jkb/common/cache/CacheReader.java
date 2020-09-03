package com.jkb.common.cache;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author zhangliang
 * @date 2019/11/20.
 */
public abstract class CacheReader<T> {
    protected CacheContainer cacheContainer;

    public CacheReader(CacheContainer cacheContainer) {
        this.cacheContainer = cacheContainer;
    }

    /**
     * 获取缓存的Key
     *
     * @return
     */
    protected abstract String getCacheKey();

    /**
     * 从数据源获取数据
     *
     * @return
     */
    protected abstract T readFromSource();

    /**
     * 获取缓存到期时长
     *
     * @return
     */
    protected abstract int getExpiredSeconds();

    private Class<T> clazz;
    private TypeReference<T> typeRef;

    /**
     * 从缓存中获取数据
     *
     * @return
     */
    protected Pair<Boolean, T> getFromCache() {
        Object objData = cacheContainer.get(getCacheKey());
        if (objData == null) {
            return Pair.of(false, null);
        } else {
            T data = null;
            if (null != this.clazz) {
                data = JSON.parseObject(String.valueOf(objData), this.clazz);
            } else if (null != this.typeRef) {
                data = JSON.parseObject(String.valueOf(objData), this.typeRef);
            }
            return Pair.of(true, data);
        }
    }

    /**
     * 将数据写入缓存
     *
     * @param data 将要写入缓存的数据
     * @return
     */
    protected Boolean setToCache(T data) {
        cacheContainer.add(getCacheKey(), JSON.toJSONString(data), getExpiredSeconds());
        return true;
    }

    /**
     * 读取数据
     *
     * @return 返回读取到的数据
     */
    public T read() {
        Pair<Boolean, T> pair = getFromCache();
        T data = pair.getValue();
        if (pair.getKey()) {
            return data;
        }

        data = readFromSource();
        setToCache(data);

        return data;
    }

    /**
     * 读取数据
     *
     * @return 返回读取到的数据
     */
    public T read(Class<T> clazz) {
        this.clazz = clazz;
        return read();
    }

    /**
     * 读取数据
     *
     * @return 返回读取到的数据
     */
    public T read(TypeReference<T> typeRef) {
        this.typeRef = typeRef;
        return read();
    }
}
