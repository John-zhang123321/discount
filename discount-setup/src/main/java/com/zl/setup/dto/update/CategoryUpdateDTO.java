package com.zl.setup.dto.update;

import lombok.Data;

/**
 * Created by zhangliang on 2019/8/21
 */
@Data
public class CategoryUpdateDTO {
    private String id;
    /**
     * 标题
     */
    private String title;

    /**
     * 备注
     */
    private String remarks;

}
