package com.zl.setup.constant;

import lombok.Getter;

/**
 * Created by zhangliang on 2019/5/24
 * 审核类型
 */
@Getter
public enum AuditTypeContract {
    MERCHANT_AUDIT("商户入驻审核");

    private String type;

    AuditTypeContract(String type) {
        this.type = type;
    }

}


