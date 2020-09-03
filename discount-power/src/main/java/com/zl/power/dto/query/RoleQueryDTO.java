package com.zl.power.dto.query;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Created by zhangliang on 2019/9/23
 */
@Data
public class RoleQueryDTO {

    @Min(value = 1,message = "pageNum 大于等于1")
    private int pageNum = 1;

    @Min(value = 1,message = "pageSize 大于等于1")
    private int pageSize = 10;

    private String name;

}
