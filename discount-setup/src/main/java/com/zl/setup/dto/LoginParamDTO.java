package com.zl.setup.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by zhangliang on 2019/7/28
 */
@Data
public class LoginParamDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
