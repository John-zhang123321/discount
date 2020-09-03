package com.zl.power.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.power.bo.AuthorizeBO;
import com.zl.power.bo.PermissionBO;
import com.zl.power.mapper.*;
import com.zl.power.service.AuthorizeService;
import com.zl.power.utils.PermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangliang on 2019/5/24
 */
@Slf4j
@Service
public class AuthorizeServiceImpl  implements AuthorizeService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    PermissionMapper permissionMapper;


    @Override
    public AuthorizeBO getAuthorizeInfo(long uid) {
        List<PermissionBO> permissionBOS = permissionMapper.getPermissionsByPermissionIds(uid);
        if (CollectionUtil.isEmpty(permissionBOS)) {
            return null;
        }
        return PermissionUtil.getAuthorizeInfo(permissionBOS);
    }
}
