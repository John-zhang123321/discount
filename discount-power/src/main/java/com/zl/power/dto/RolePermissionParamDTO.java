package com.zl.power.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zhangliang on 2019/7/30
 */
@Data
public class RolePermissionParamDTO {
    @NotNull(message = "角色id不能为空")
    private long roleId;
    private List<Long> permissionIds;
}
