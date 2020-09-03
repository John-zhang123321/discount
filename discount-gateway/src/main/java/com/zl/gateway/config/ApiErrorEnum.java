package com.zl.gateway.config;

import lombok.Getter;

/**
 * @author zhangliang
 * @date 2020/6/28
 */

public enum ApiErrorEnum {
    BAD_REQUEST_APPID_NULL_OR_EMPTY(100001, "[_appid] must not be null or empty"),
    BAD_REQUEST_APPID_INVALID(100002, "[_appid] is invalid"),


    BAD_REQUEST_TIMESTAMP_NULL_OR_EMPTY(100003, "[_timestamp] must not be null or empty"),
    BAD_REQUEST_TIMESTAMP_EXPIRED(100004, "[_timestamp] request expired"),
    BAD_REQUEST_TIMESTAMP_INVALID(100005, "[_timestamp] is invalid"),

    BAD_REQUEST_SIGN_NULL_OR_EMPTY(100006, "[_sign] must not be null or empty"),
    BAD_REQUEST_SIGN_INVALID(100007, "[_sign] is invalid"),

    TOKEN_EXPIRE(100008, "token expired"),
    OVER_MANY(100009,"请求访问受限."),

    TOKEN_INVALID(100010, "token must not be null or empty"),

    UNAUTHORIZED(100011,"您没有登录，请登录后操作");
     ApiErrorEnum(int code, String errMsg) {
        this.code = code;
        this.errMsg = errMsg;
    }

    private int code;
    private String errMsg;

    public int getCode() {
        return code;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
