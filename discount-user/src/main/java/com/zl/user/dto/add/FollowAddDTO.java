package com.zl.user.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2019-12-01
*/
@Data
public class FollowAddDTO{
    // 关注人
    private Long userId;

    // 店铺id
    @NotNull(message = "字段 不能为空")
    private Long shopId;
}