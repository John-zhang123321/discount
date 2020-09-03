package com.zl.user.dto.query;

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
public class CouponQueryDTO{

    private Long userId;

    private int pageNum = 1;

    private int pageSize = 10;




}