package com.zl.setup.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;

import java.util.List;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopAddDTO{
    // 所属用户id
    private Long userId;

    // 所属分类id
    @NotNull(message = "分类id不能为空")
    private Long categoryId;

    // 店铺名称
    @NotBlank(message = "字段 不能为空")
    private String shopName;

    // 城市编码
    @NotBlank(message = "字段 不能为空")
    private String cityCode;

    // 省份
    @NotBlank(message = "字段 不能为空")
    private String provinceName;

    // 城市
    @NotBlank(message = "字段 不能为空")
    private String cityName;

    // 地区
    @NotBlank(message = "字段 不能为空")
    private String areaName;

    // 详细地址
    @NotBlank(message = "字段 不能为空")
    private String detail;

    // 电话
    @NotBlank(message = "字段 不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String phone;

    // 联系人
    @NotBlank(message = "字段 不能为空")
    private String contact;

    // 经度
    @NotNull(message = "字段 不能为空")
    @Range(min = -180,max = 180,message = "经度范围 -180.0, 180.0")
    private Double lon;

    // 纬度
    @NotNull(message = "字段 不能为空")
    @Range(min = -90,max = 90,message = "纬度范围 -90.0, 90.0")
    private Double lat;

    // 状态 0.正常 1.异常
    private Integer status;

    @Size(min = 2,message = "字段数量异常")
    private List<Long> shopImgIds;

}