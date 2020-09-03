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
public class GoodsLittleBO {

    private Long id;

    private String name;

    private String remark;

    private BigDecimal originalPrice;

    private BigDecimal presentPrice;

    private Date startTime;

    private Date endTime;

    private List<GoodsImgBO> goodsImgBOS;

}