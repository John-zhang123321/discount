package com.zl.power.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/7/15
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionVo {
    private String permissionId;
    private String roleId;
    private String name;
    private String alias;
    private String component;;
    private String pid;
    private String path;
    private Integer type;
    private Integer sort;
    private String icon;
    private String label;
    private Map meta;
    private List<PermissionVo> children;
}
