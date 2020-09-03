package com.zl.setup.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-20
*/
@Data
@Table(name="shop")
public class Shop implements Serializable {

    private static final long serialVersionUID = -7334969451869598160L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 所属用户id
    @Column(name = "user_id")
    private Long userId;

    // 分类id
    @Column(name = "category_id")
    private Long categoryId;

    // 店铺名称
    @Column(name = "shop_name")
    private String shopName;

    // 城市编码
    @Column(name = "city_code")
    private String cityCode;

    // 省份
    @Column(name = "province_name")
    private String provinceName;

    // 城市
    @Column(name = "city_name")
    private String cityName;

    // 地区
    @Column(name = "area_name")
    private String areaName;

    // 详细地址
    @Column(name = "detail")
    private String detail;

    // 电话
    @Column(name = "phone")
    private String phone;

    // 联系人
    @Column(name = "contact")
    private String contact;

    // 经度
    @Column(name = "lon")
    private Double lon;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    @Column(name = "status")
    private Integer status;

    private String reason;

    private Double rate;
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