package com.zl.setup.dto.update;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopAuditDTO {

    @NotNull(message = "id 不能为空")
    private Long id;

    private Integer status;

    private String reason;

    @NotBlank(message = "cityCode不能为空")
    private String cityCode;

    @NotBlank(message = "phone不能为空")
    private String phone;

    @NotNull(message = "userId不能为空")
    private Long userId;
}