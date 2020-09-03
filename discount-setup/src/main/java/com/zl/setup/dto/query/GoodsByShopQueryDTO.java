package com.zl.setup.dto.query;

import lombok.Data;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class GoodsByShopQueryDTO {

    private Long shopId;

    private int pageNum;

    private int pageSize;

}