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
public class ExpiredGoodsBO {

    private Long id;

    private Long shopId;

    private Long userId;

    private List<GoodsImgBO> goodsImgBOS;

}