package com.zl.setup.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class GoodsBO {

    private Long id;

    private Long shopId;

    private String name;

    private String remark;

    private Integer love;

    private Integer sale;

    private BigDecimal originalPriceMax;

    private BigDecimal originalPriceMin;

    private BigDecimal presentPriceMax;

    private BigDecimal presentPriceMin;

    private Integer proportion;

    private Date startTime;

    private Date endTime;

    private int obtain;

    private List<GoodsImgBO> goodsImgBOS;

}