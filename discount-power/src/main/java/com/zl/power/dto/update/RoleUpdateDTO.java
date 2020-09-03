package com.zl.power.dto.update;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangliang on 2019/10/10
 */
@Data
public class RoleUpdateDTO {
    @NotNull(message = "id 不能为空")
    private long id;

    private String name;

    private String remark;
}
