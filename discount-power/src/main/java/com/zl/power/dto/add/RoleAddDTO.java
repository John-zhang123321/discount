package com.zl.power.dto.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by zhangliang on 2019/10/10
 */
@Data
public class RoleAddDTO {

    @NotBlank(message = "name 字段不能为空")
    private String name;

    private String remark;
}
