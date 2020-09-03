package com.zl.user.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by zhangliang on 2019/10/17
 */
@Data
public class ManagerBo {
    /**
     * 编号
     */
    private Long id;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String username;

    private String phone;

    /**
     * 1:正常,0:锁定
     */
    private Boolean locked;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    private String provinceCode;

    private String cityCode;

    private String regionName;

    private Long roleId;

}
