package com.zl.setup.bo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopBO {

    private Long id;

    private Long userId;

    private String shopName;

    private String provinceName;

    private String cityName;

    private String areaName;

    private String cityCode;

    private String detail;

    private String phone;

    private Double lon;

    private Double lat;

    private String contact;

    private Integer status;

    private String reason;

    private Long categoryId;

    private String categoryTitle;

    private Integer distance;

    private List<ShopImgBO> shopImgBOS;

}