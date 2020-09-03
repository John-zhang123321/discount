package com.zl.setup.dto.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by zhangliang on 2019/8/21
 */
@Data
public class CategoryAddDTO {

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remarks;
}
