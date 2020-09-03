package com.jkb.common.callback;

import cn.hutool.core.bean.BeanUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/12/9
 */
public interface Callback<T,P> {

    List<T> getData(P param);

    List<T> sorted(List<T> data,P param);

    default Map<String,T> toMap(List<T> data){
        return data.stream().collect(Collectors.toMap(o -> BeanUtil.getFieldValue(o,"id").toString(), v -> v));
    }

}
