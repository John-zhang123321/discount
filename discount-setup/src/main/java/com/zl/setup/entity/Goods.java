package com.zl.setup.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
@Table(name="goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 7794407003198992045L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;
    // 店铺id
    @Column(name = "shop_id")
    private Long shopId;
    
    // 商品名称
    @Column(name = "name")
    private String name;

    private Integer proportion;
    // 描述
    private String remark;

    private List<Long> goodImgIdList;

    @Column(name = "original_price_max")
    private BigDecimal originalPriceMax;

    @Column(name = "original_price_min")
    private BigDecimal originalPriceMin;

    @Column(name = "present_price_max")
    private BigDecimal presentPriceMax;

    @Column(name = "present_price_min")
    private BigDecimal presentPriceMin;

    // 折扣开始时间
    @Column(name = "start_time")
    private Date startTime;

    // 折扣结束时间
    @Column(name = "end_time")
    private Date endTime;

    private Integer love;

    private Integer sale;

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