package com.zl.user.dto.add;

import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
* @author zhangliang
* @date 2019-12-05
*/
@Data
public class CommentImgAddDTO{
    // 店铺id
    private Long commentId;

    // url
    private String url;

}