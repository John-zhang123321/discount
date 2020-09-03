package com.zl.power.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangliang on 2019/9/24
 */
@Data
public class RolePermissionBO {
    private long roleId;

    private List<PermissionLittleBO> permissionLittleBOS;
}
