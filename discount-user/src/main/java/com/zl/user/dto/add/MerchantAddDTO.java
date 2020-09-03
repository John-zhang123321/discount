package com.zl.user.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;


/**
* @author zhangliang
* @date 2019-11-15
*/
@Data
public class MerchantAddDTO{

    @NotBlank(message = "phone不能为空")
    private String phone;

    @NotNull
    private Long customerId;

    @NotBlank
    private String cityCode;
}