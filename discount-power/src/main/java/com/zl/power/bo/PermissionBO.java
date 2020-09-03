package com.zl.power.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/9/24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionBO {

    private Long id;

    private String alias;

    private String name;

    private Long pid;

    private Integer type;

    private String icon;

    private String path;

    private String sort;

    private String component;

    private Date createTime;

    private Map meta;

    private List<PermissionBO> children;
}
