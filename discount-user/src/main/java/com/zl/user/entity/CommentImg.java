package com.zl.user.entity;

import lombok.Data;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
* @author zhangliang
* @date 2019-12-05
*/
@Data
@Table(name="comment_img")
public class CommentImg implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 店铺id
    @Column(name = "comment_id")
    private Long commentId;

    // url
    @Column(name = "url")
    private String url;

    // 创建人
    @Column(name = "create_user")
    private Long createUser;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

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