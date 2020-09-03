package com.jkb.common.utils;

import lombok.Data;

import java.util.Map;

/**
 * Created by zhangliang on 2019/7/6
 */
@Data
public class WxMssDTO {
    private String touser;//用户openid
    private String template_id;//模版id
    private String page = "index";//默认跳到小程序首页
    private Map<String, TemplateData> data;//推送文字
}
