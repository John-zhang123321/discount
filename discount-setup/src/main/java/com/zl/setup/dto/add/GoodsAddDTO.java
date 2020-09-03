package com.zl.setup.dto.add;

import javax.persistence.Column;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Range;
import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class GoodsAddDTO{
    private Long userId;

    // 店铺id
//    @NotNull(message = "字段 不能为空")
    private Long shopId;

    // 商品名称
    @NotBlank(message = "name 不能为空")
    private String name;

    // 描述
    @NotBlank(message = "remark 不能为空")
    private String remark;

    @NotNull(message = "originalPriceMax 不能为空")
    @Min(value= 0)
    private BigDecimal originalPriceMax;

    @NotNull(message = "originalPriceMin 不能为空")
    @Min(value= 0)
    private BigDecimal originalPriceMin;

    @NotNull(message = "presentPriceMax 不能为空")
    @Min(value= 0)
    private BigDecimal presentPriceMax;

    @NotNull(message = "presentPriceMin 不能为空")
    @Min(value= 0)
    private BigDecimal presentPriceMin;

    @NotNull(message = "highest 不能为空")
    @Range(min = 0, max = 100)
    private Integer proportion;

    // 折扣开始时间
    @NotNull(message = "startTime 不能为空")
    private Date startTime;

    // 折扣结束时间
    @NotNull(message = "endTime 不能为空")
    private Date endTime;

    private Integer love;

    private Integer sale;

    private List<Long> goodImgIdList;
}