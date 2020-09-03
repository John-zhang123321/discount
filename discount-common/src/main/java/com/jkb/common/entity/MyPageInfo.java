package com.jkb.common.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangliang on 2019/11/27
 */
@Data
public class MyPageInfo<T> {
    private List<T> list = new ArrayList<>();

    private boolean hasNext;
}
