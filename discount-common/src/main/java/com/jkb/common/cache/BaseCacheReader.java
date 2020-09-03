package com.jkb.common.cache;

/**
 * @author zhangliang
 * @date 2018/3/21.
 */
public abstract class BaseCacheReader<T> extends CacheReader<T> {

    public BaseCacheReader(CacheContainer cacheContainer) {
        super(cacheContainer);
    }

//    /**
//     * 是否使用缓存
//     */
//    protected Boolean isCache = true;

    /**
     * 读取数据
     *
     * @return 返回读取到的数据
     */
    public T read() {
        return read(true);
    }

    /**
     * 读取数据
     *
     * @param fromCache 是否不使用缓存
     * @return 返回读取到的数据
     */
    public T read(Boolean fromCache) {
        if (fromCache) {
            return super.read();
        }
        return readFromSource();
    }

    /**
     * 将数据设置到缓存中
     *
     * @param data 数据
     * @return 返回是否写入成功
     */
    protected Boolean setToCache(T data) {
        if (data == null) {
            return false;
        }
        return super.setToCache(data);
    }
}
