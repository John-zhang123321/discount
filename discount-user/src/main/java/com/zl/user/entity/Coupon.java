package com.zl.user.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
* @author zhangliang
* @date 2019-12-26
*/
@Data
@Table(name="coupon")
public class Coupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shop_id")
    private Long shopId;

    private String shopName;

    private String shopDetail;

    private String shopUrl;

    // 商品id
    @Column(name = "goods_id")
    private Long goodsId;

    private String goodsName;

    private String goodsUrl;

    // 用户id
    @Column(name = "user_id")
    private Long userId;

    private Integer proportion;

    private Integer status;

    private String qrCode;

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