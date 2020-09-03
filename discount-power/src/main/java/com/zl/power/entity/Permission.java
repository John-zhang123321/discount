package com.zl.power.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "permission")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人",hidden = true)
    @Column(name = "create_user")
    @JsonIgnore
    private Long createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",hidden = true)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @ApiModelProperty(value="更新人",hidden = true)
    @Column(name = "update_user")
    @JsonIgnore
    private Long updateUser;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",hidden = true)
    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;
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