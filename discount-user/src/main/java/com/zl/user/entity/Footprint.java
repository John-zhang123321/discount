package com.zl.user.entity;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "footprint")
public class Footprint {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "shop_id")
    private Long shopId;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     * 访问时间
     */
    @Column(name = "access_time")
    private Date accessTime;


}