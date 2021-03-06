package com.zl.user.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2019-12-26
*/
@Data
public class CouponUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    private Long goodsId;

    private Long userId;
}