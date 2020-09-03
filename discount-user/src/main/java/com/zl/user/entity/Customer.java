package com.zl.user.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-18
*/
@Data
@Table(name="customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 微信id
    @Column(name = "open_id")
    private String openId;

    // 手机号
    @Column(name = "phone")
    private String phone;

    // 国家
    @Column(name = "country")
    private String country;

    // 省份
    @Column(name = "province")
    private String province;

    // 城市
    @Column(name = "city")
    private String city;

    // 昵称
    @Column(name = "nick_name")
    private String nickName;

    // 头像
    @Column(name = "avatar_url")
    private String avatarUrl;

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


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", locked=" + locked +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                '}';
    }
}