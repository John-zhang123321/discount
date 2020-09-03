package com.zl.setup.dto.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopSearchDTO {

    private int pageNum = 1;

    private int pageSize = 10;

    private String goodsName;

}