package com.jkb.common.entity;

import com.jkb.common.callback.Callback;
import lombok.Data;

/**
 * Created by zhangliang on 2019/12/12
 */
@Data
public class WrapperParam<P> {

    private Object key;

    private Callback callback;

    private P param;

    private int start;

    private int end;

    public WrapperParam(Object key, Callback callback, P param,int start,int end) {
        this.key = key;
        this.callback = callback;
        this.param = param;
        this.start = start;
        this.end = end;
    }

}
