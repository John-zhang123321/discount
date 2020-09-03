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
@Table(name="shop_img")
public class ShopImg implements Serializable {

    private static final long serialVersionUID = 7892779152058918125L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 店铺id
    @Column(name = "shop_id")
    private Long shopId;

    // url
    @Column(name = "url")
    private String url;

    // 0.店铺图片 1.营业执照
    @Column(name = "type")
    private Integer type;

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