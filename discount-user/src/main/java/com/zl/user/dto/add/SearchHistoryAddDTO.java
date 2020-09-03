package com.zl.user.dto.add;

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
public class SearchHistoryAddDTO{
    // 用户id
    private Long userId;

    // 关键字
    private String keywords;

}