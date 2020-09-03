package com.zl.power.dto.update;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by zhangliang on 2019/10/10
 */
@Data
public class PermissionUpdateDTO {

    @NotNull(message = "id 不能为空")
    private Long id;
    /**
     * 别名
     */
    private String alias;

    /**
     * 名称
     */
    private String name;

    /**
     * 上级权限
     */
    private Long pid;

    /**
     * 组件
     */
    private String component;

    /**
     * 类型0.菜单1.按钮
     */
    private Integer type;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 图标
     */
    private String icon;

    /**
     * 链接地址
     */
    private String path;
}
