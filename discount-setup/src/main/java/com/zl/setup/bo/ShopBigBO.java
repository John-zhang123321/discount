package com.zl.setup.bo;

import lombok.Data;

import java.util.List;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopBigBO {

    private Long id;

    private String shopName;

    private String provinceName;

    private String cityName;

    private String areaName;

    private String detail;

    private String phone;

    private Double lon;

    private Double lat;

    private String contact;

    private Integer status;

    private String reason;

    private Long categoryId;

    private String categoryTitle;

    private List<ShopImgBO> shopImgBOS;

    private Long userId;

    private String nickName;
}