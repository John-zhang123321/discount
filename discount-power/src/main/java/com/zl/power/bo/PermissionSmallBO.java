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
public class PermissionSmallBO {
    private String id;
    private String pid;
    private Integer type;
    private String label;
    private String path;
    private Integer sort;
    private String component;
    private String alias;
    private String name;
    private String icon;
    private Date createTime;

    private List<PermissionSmallBO> children;
}
