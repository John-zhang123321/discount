package com.zl.user.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;


/**
* @author zhangliang
* @date 2019-11-18
*/
@Data
public class LoginDTO {

    private Long id;
    // 微信id
    private String openId;

    // 手机号
    private String phone;

    // 国家
    private String country;

    // 省份
    private String province;

    // 城市
    private String city;

    // 昵称
    @NotBlank(message = "nickName 字段不能为空")
    private String nickName;

    // 头像
    @NotBlank(message = "avatarUrl 字段不能为空")
    private String avatarUrl;

    // 0:正常,1:锁定
    private Boolean locked;

    @NotBlank(message = "code 字段不能为空")
    private String code;

}