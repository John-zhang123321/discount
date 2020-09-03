package com.zl.setup.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user")
@Data
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 创建人
     */
    @ApiModelProperty(value="创建人",hidden = true)
    @Column(name = "create_user")
    @JsonIgnore
    private String createUser;

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
    private String updateUser;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",hidden = true)
    @Column(name = "update_time")
    @JsonIgnore
    private Date updateTime;
    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private String username;

    @Column(name = "dept_id")
    private String deptId;

    private String phone;

    @Column(name = "job_id")
    private String jobId;

    /**
     * 1:正常,0:锁定
     */
    private Byte enabled;

}