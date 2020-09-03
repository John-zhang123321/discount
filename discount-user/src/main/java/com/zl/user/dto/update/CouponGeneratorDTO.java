package com.zl.user.dto.update;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* @author zhangliang
* @date 2019-12-26
*/
@Data
public class CouponGeneratorDTO {

    @NotNull(message = "id 不能为空")
    private Long id;
}