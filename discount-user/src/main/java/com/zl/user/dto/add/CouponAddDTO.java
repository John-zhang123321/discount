package com.zl.user.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2019-12-26
*/
@Data
public class CouponAddDTO{
    @NotNull(message = "shopId 不能为空")
    private Long shopId;

    private String shopName;

    private String shopDetail;

    private String shopUrl;

    @NotNull(message = "goodsId 不能为空")
    private Long goodsId;

    private String goodsName;

    private String goodsUrl;
    // 用户id
    private Long userId;

}