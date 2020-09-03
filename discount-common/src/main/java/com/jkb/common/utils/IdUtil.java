package com.jkb.common.utils;

/**
 * Created by zhangliang on 2019/9/25
 */
public class IdUtil {
    /**
     *
     * @param workerId 终端ID
     * @param datacenterId 数据中心ID
     */
    public static long nextId(long workerId, long datacenterId) {
        return cn.hutool.core.util.IdUtil.createSnowflake(workerId, datacenterId).nextId();
    }

    public static long nextId() {
        return nextId(1, 1);
    }
}
