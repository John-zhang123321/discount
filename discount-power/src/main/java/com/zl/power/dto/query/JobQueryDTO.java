package com.zl.power.dto.query;

import lombok.Data;

/**
 * Created by zhangliang on 2019/8/3
 */
@Data
public class JobQueryDTO {
    private String name;

    private String deptId;

    private int page = 1;

    private int size = 10;
}
