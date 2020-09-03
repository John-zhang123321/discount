package com.zl.setup.bo;

import lombok.Data;

/**
 * Created by zhangliang on 2019/8/21
 */
@Data
public class ReviewQueryBO {

    private String shopId;

    private Byte status;

    private String reason;

}
