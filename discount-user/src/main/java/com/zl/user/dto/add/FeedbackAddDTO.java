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
public class FeedbackAddDTO{
    // 内容
    @NotBlank(message = "字段 不能为空")
    private String content;

    // 联系方式
    @NotBlank(message = "字段 不能为空")
    private String contact;

}