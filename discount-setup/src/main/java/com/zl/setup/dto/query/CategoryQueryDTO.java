package com.zl.setup.dto.query;

import lombok.Data;

/**
 * Created by zhangliang on 2019/8/21
 */
@Data
public class CategoryQueryDTO {

    private String title;

    private int pageNum = 1;

    private int pageSize = 10;
}
