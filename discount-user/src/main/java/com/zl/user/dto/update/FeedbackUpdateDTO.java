package com.zl.user.dto.update;

import lombok.Data;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2019-12-01
*/
@Data
public class FeedbackUpdateDTO{

    @NotNull(message = "id 不能为空")
    private Long id;

    private String content;

    private String contact;
}