package com.zl.power.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-02
*/
@Data
@Table(name="dept")
public class Dept implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 名称
    @Column(name = "name")
    private String name;

    // 上级部门
    @Column(name = "pid")
    private String pid;

    @Column(name = "level")
    private Integer level;

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