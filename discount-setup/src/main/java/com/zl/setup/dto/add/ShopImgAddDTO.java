package com.zl.setup.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopImgAddDTO{
    // 店铺id
    @NotNull(message = "字段 不能为空")
    private Long shopId;

    // url
    @NotBlank(message = "字段 不能为空")
    private String url;

    // 0.店铺图片 1.营业执照
    @NotNull(message = "字段 不能为空")
    private Integer type;

}