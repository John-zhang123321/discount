package com.zl.power.bo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangliang on 2019/9/24
 * 用户角色信息
 */
@Data
public class RoleBO {
    private Long id;

    private String name;

    private String remark;

    private String dataScope;

    private String level;

    private Date createTime;

    private List<PermissionLittleBO> permissionLittleBOS;

}
