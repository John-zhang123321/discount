package com.zl.user.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;


/**
* @author zhangliang
* @date 2019-11-18
*/
@Data
public class CustomerUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    @NotBlank(message = "字段 不能为空")
    private String openId;

    @NotBlank(message = "字段 不能为空")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号格式错误")
    private String phone;

    private String country;

    private String province;

    private String city;

    private String nickName;

    private String avatarUrl;

    private Boolean locked;
}