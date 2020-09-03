package com.zl.user.bo;

import lombok.Data;

import java.util.List;

/**
* @author zhangliang
* @date 2019-12-26
*/
@Data
public class CouponGoodsBO {
    private Long id;

    private String name;

    private List<Long> goodImgIdList;

}