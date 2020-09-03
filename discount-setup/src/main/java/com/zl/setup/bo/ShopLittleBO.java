package com.zl.setup.bo;

import lombok.Data;

import java.util.List;

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

    private Double rate;

    private Long categoryId;

    private String categoryTitle;

    private Double distance;

    private String url;

}