package com.zl.user.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "user")
@Data
public class User {

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 值为1时是男性，值为2时是女性，值为0时是未知
     */
    private String gender;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在国家
     */
    private String country;


    /**
     * 用户类型 0.消费者 1.商户
     */
    @Column(name = "user_type")
    private Integer userType;


}