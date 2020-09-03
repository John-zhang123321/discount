package com.zl.power.dto.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
* @author zhangliang
* @date 2019-11-02
*/
@Data
public class UserRoleAddDTO implements Serializable {
    // 名称
    @NotNull(message = "userId 不能为空")
    private Long userId;

    @NotNull(message = "roleId 不能为空")
    private Long roleId;


}