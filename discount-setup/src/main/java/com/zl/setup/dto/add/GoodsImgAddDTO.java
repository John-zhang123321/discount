package com.zl.setup.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class GoodsImgAddDTO{
    // 商品id
    private Long goodsId;

    // url
    @NotNull(message = "字段 不能为空")
    private String url;

}