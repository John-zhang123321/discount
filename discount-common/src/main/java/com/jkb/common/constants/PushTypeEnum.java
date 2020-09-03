package com.jkb.common.constants;

/**
 * Created by zhangliang on 2019/5/23
 * 推送类型 枚举
 */
public enum PushTypeEnum {
    SELLER("seller"),

    BUYER("buyer");

    private String type;

    private PushTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String setType(String type) {
        return this.type = type;
    }
}
