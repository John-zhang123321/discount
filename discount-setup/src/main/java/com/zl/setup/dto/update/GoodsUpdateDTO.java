package com.zl.setup.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class GoodsUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    private Long shopId;

    private String name;

    private String desc;

    @NotNull(message = "字段 不能为空")
    private BigDecimal originalPrice;

    @NotNull(message = "字段 不能为空")
    private BigDecimal presentPrice;

    @NotNull(message = "字段 不能为空")
    private Timestamp startTime;

    @NotNull(message = "字段 不能为空")
    private Timestamp endTime;
}