package com.zl.user.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2020-01-04
*/
@Data
public class SearchHistoryUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    private Long userId;

    private String keywords;
}