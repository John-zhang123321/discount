package com.zl.setup.dto.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
* @author zhangliang
* @date 2019-11-15
*/
@Data
public class MerchantAddDTO {

    private String phone;

    private Long customerId;

    private String cityCode;
}