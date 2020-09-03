package com.jkb.common.entity;

import lombok.Data;

import javax.validation.constraints.Null;

/**
 * Created by zhangliang on 2019/8/2
 */
@Data
public class IdEntity {
    @Null
    private Long id;
}
