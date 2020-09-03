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
@Table(name="follow")
public class Follow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 关注人
    @Column(name = "user_id")
    private Long userId;

    // 店铺id
    @Column(name = "shop_id")
    private Long shopId;

    // 创建人
    @Column(name = "create_user")
    private Long createUser;

    // 创建时间
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