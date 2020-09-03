package com.zl.setup.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    private Long userId;

    private String shopName;

    private String cityCode;

    private String provinceName;

    private String cityName;

    private String areaName;

    private String detail;

    private String phone;

    private String contact;

    private Double lon;

    private Double lat;

    private Integer status;

    private Long categoryId;
}