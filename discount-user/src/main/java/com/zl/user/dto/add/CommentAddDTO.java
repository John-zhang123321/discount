package com.zl.user.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
* @author zhangliang
* @date 2019-12-05
*/
@Data
public class CommentAddDTO{
    // 店铺id
    @NotNull(message = "优惠券id不能为空")
    private Long couponId;

    // 店铺id
    @NotNull(message = "字段 不能为空")
    private Long shopId;

    // 内容
    @NotBlank(message = "字段 不能为空")
    private String content;

    // 评分
    private Integer rate;

    // 创建人
    private Long createUser;

    private List<Long> commentImgIdList;

}