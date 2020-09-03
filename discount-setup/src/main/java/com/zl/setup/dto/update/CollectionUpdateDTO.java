package com.zl.setup.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class CollectionUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    private Long userId;

    private Long shopId;
}