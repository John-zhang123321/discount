package com.zl.power.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangliang on 2019/7/15
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionTreeBO {
    private String id;
    private String pid;
    private Integer type;
    private String alias;

    private List<PermissionTreeBO> children;
}
