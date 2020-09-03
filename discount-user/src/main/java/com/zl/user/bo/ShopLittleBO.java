package com.zl.user.bo;

import lombok.Data;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopLittleBO {

    private Long shopId;

    private String shopName;

    private String areaName;

    private String detail;

    private String phone;

    private Double lon;

    private Double lat;

    private Integer rate;

    private String categoryTitle;

    private Double distance;

    private String url;

}