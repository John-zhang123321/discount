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
@Table(name="goods_img")
public class GoodsImg implements Serializable {

    private static final long serialVersionUID = 3046651204155502640L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 商品id
    @Column(name = "goods_id")
    private Long goodsId;

    // url
    @Column(name = "url")
    private String url;

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