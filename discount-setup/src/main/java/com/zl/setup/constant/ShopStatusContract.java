package com.zl.setup.constant;

import lombok.Getter;

/**
 * Created by zhangliang on 2019/5/24
 */
@Getter
public enum ShopStatusContract {
    NAUDIT(0,"正在审核中"),

    AUDIT(1,"审核通过"),

    AUDIT_FAILED(2,"审核不通过");

    private int status;

    private String result;

    private ShopStatusContract(int status, String result) {
        this.status = status;
        this.result = result;
    }

}


