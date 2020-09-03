package com.zl.user.bo;

import lombok.Data;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-12-26
*/
@Data
public class CouponBO {
    private Long id;

    private String qrCode;

    private Integer status;

    private Integer proportion;


    private Long shopId;

    private Long goodsId;

    private String shopName;

    private String shopDetail;

    private String shopUrl;

    private String goodsName;

    private String goodsUrl;

    private Long userId;


}