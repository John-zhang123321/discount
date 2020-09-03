package com.zl.setup.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2020-01-04
*/
@Data
public class HotSearchAddDTO{
    // 店铺id
    private Long shopId;

    // 次数
    private Integer count;

}