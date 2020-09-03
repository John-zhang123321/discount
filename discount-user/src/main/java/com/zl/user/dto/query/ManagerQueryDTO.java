package com.zl.user.dto.query;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Created by zhangliang on 2019/7/6
 */
@Data
public class ManagerQueryDTO {
    private String username;

    private String cityName;

    private Boolean locked;

    @Min(value = 1,message = "pageNum 大于等于1")
    private int pageNum = 1;

    @Min(value = 1,message = "pageSize 大于等于1")
    private int pageSize = 10;
}
