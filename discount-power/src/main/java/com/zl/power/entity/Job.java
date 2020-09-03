package com.zl.power.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "job")
@Data
public class Job {

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

    private String name;

    private Integer sort;

    @Column(name = "dept_id")
    private Long deptId;


}