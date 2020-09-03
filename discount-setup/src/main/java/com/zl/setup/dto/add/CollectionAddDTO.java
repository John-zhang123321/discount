package com.zl.setup.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class CollectionAddDTO{
    // 收藏人
    @NotNull(message = "字段 不能为空")
    private Long userId;

    // 店铺id
    @NotNull(message = "字段 不能为空")
    private Long shopId;

}