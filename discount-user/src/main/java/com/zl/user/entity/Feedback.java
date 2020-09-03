package com.zl.user.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
* @author zhangliang
* @date 2019-12-01
*/
@Data
@Table(name="feedback")
public class Feedback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 内容
    @Column(name = "content")
    private String content;

    // 联系方式
    @Column(name = "contact")
    private String contact;

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