package com.zl.setup.dto.query;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
public class ShopQuery4CategoryDTO {

    @NotBlank(message = "lonlat 不能为空")
    private String lonlat;

    @NotNull(message = "categoryId 不能为空")
    private Long categoryId;

    private int pageNum = 1;

    private int pageSize = 10;

}