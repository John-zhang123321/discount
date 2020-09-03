package com.jkb.common.response;

import lombok.Data;

/**
 * Created by zhangliang on 2019/11/18
 */
@Data
public class WxResponse {
    private String access_token;

    private String openid;
}
