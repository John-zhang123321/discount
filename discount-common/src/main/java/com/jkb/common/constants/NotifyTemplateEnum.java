package com.jkb.common.constants;

import lombok.Getter;

/**
 * Created by zhangliang on 2019/10/21
 */
@Getter
public enum NotifyTemplateEnum {
    TO_MANAGER ("认证通知","<p>用户<span style='color:red'>{}</span>申请了商户认证,请尽快审核<p/> "),

    TO_BUYER4FAILURE ("系统通知","您的企业认证被驳回了,原因是:"),

    TO_BUYER4SUCCESS("系统通知", "恭喜!!!您的企业认证已经通过了");


    private String type;

    private String content;

    private NotifyTemplateEnum(String type, String content) {
        this.type = type;
        this.content = content;
    }
}
