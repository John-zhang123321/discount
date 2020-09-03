package com.jkb.common.base;

import lombok.Getter;

/**
 * @author zhangliang
 * @date 2020/6/28
 */
@Getter
public enum ErrRespEnum {
    WECHAT_PUSH_ERR(200001, "[_appid] must not be null or empty"),
    TOKEN_INVALID(100009, "token must not be null or empty");

     ErrRespEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;
}
