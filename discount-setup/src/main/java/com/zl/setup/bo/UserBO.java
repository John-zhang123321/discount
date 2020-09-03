package com.zl.setup.bo;

import lombok.Data;

import java.util.Date;

/**
 * Created by zhangliang on 2019/8/21
 */
@Data
public class UserBO {
    private long id;
    /**
     * 帐号
     */
    private String username;


    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    private long jobId;

    private Date createTime;

    private Boolean Locked;

    private long deptId;

}
