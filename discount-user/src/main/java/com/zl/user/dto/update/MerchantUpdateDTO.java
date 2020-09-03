package com.zl.user.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;


/**
* @author zhangliang
* @date 2019-11-15
*/
@Data
public class MerchantUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    private Long shopId;

    @NotBlank(message = "字段 不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String phone;

    private String password;

    @NotBlank(message = "字段 不能为空")
    private String nickName;

    private String avatarUrl;

    private Integer provinceId;

    private String provinceName;

    private Integer cityId;

    private String cityName;

    private Integer areaId;

    private String areaName;

    @NotBlank(message = "字段 不能为空")
    private String detailAddress;

    private String cityCode;

    @NotNull(message = "字段 不能为空")
    @Range(min = -180,max = 180,message = "经度范围 -180.0, 180.0")
    private Double lon;

    @NotNull(message = "字段 不能为空")
    @Range(min = -90,max = 90,message = "纬度范围 -90.0, 90.0")
    private Double lat;

    private Boolean locked;
}