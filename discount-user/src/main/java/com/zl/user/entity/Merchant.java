package com.zl.user.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-15
*/
@Data
@Table(name="merchant")
public class Merchant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "open_id")
    private String openId;

    // 手机号
    @Column(name = "phone")
    private String phone;

    // 密码
    @Column(name = "password")
    private String password;

    // 用户昵称
    @Column(name = "nick_name")
    private String nickName;

    // 头像地址
    @Column(name = "avatar_url")
    private String avatarUrl;

    // 用户所在省份id
    @Column(name = "province_id")
    private Integer provinceId;

    // 用户所在省份名称
    @Column(name = "province_name")
    private String provinceName;

    // 城市id
    @Column(name = "city_id")
    private Integer cityId;

    // 城市名称
    @Column(name = "city_name")
    private String cityName;

    // 区id
    @Column(name = "area_id")
    private Integer areaId;

    // 区名
    @Column(name = "area_name")
    private String areaName;

    // 详细地址
    @Column(name = "detail_address")
    private String detailAddress;

    // 城市编码
    @Column(name = "city_code")
    private String cityCode;

    // 经度
    @Column(name = "lon")
    private Double lon;

    // 纬度
    @Column(name = "lat")
    private Double lat;

    // 0:正常,1:锁定
    @Column(name = "locked")
    private Boolean locked;

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