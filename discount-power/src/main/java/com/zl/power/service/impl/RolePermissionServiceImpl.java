package com.zl.power.service.impl;

import com.zl.common.utils.BeanUtil;
import com.zl.power.dto.RolePermissionParamDTO;
import com.zl.power.entity.RolePermission;
import com.zl.power.mapper.RolePermissionMapper;
import com.zl.power.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrAdd(RolePermissionParamDTO rolePermissionParamDTO) {
        rolePermissionMapper.deleteByRoleId(rolePermissionParamDTO.getRoleId());
        for (Long permissionId : rolePermissionParamDTO.getPermissionIds()) {
            RolePermission rolePermission = BeanUtil.copyProperties(rolePermissionParamDTO, RolePermission.class, true);
            rolePermission.setPermissionId(permissionId);
            rolePermissionMapper.insertSelective(rolePermission);
        }
    }
}
