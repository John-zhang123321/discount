package com.zl.user.dto.update;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by zhangliang on 2019/7/6
 */
@Data
public class ManagerUpdateDTO {

    @NotNull(message = "id 不能为空")
    private Long id;

    @NotNull(message = "locked 不能为空")
    private Boolean locked;

    @Email(message="请输入正确的邮箱")
    private String email;

    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String phone;

    @NotBlank(message = "username不能为空")
    private String username;

    @NotBlank(message = "provinceCode 不能为空")
    private String provinceCode;

    @NotBlank(message = "cityCode 不能为空")
    private String cityCode;

    @NotBlank(message = "regionName 不能为空")
    private String regionName;

    @NotNull(message = "roleId 不能为空")
    private Long roleId;

}
