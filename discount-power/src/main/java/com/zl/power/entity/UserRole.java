package com.zl.power.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Table(name = "user_role")
@Data
public class UserRole {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 角色编号
     */
    @Column(name = "role_id")
    private Long roleId;

    private String type;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private Long createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新人
     */
    @Column(name = "update_user")
    private Long updateUser;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}