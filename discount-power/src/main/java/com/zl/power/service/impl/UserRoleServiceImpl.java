package com.zl.power.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.utils.BeanUtil;
import com.zl.power.bo.PermissionLittleBO;
import com.zl.power.bo.RoleBO;
import com.zl.power.bo.RolePermissionBO;
import com.zl.power.bo.UserRoleBO;
import com.zl.power.dto.add.RoleAddDTO;
import com.zl.power.dto.add.UserRoleAddDTO;
import com.zl.power.dto.query.RoleQueryDTO;
import com.zl.power.dto.update.RoleUpdateDTO;
import com.zl.power.entity.Role;
import com.zl.power.entity.UserRole;
import com.zl.power.expection.RoleException;
import com.zl.power.mapper.PermissionMapper;
import com.zl.power.mapper.RoleMapper;
import com.zl.power.mapper.RolePermissionMapper;
import com.zl.power.mapper.UserRoleMapper;
import com.zl.power.service.UserRoleService;
import com.zl.power.utils.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;


    @Override
    public Map<Long, UserRoleBO> getRoleIdByUid(List<Long> mids) {
        List<UserRoleBO> userRoleBOS = userRoleMapper.getRoleIdByUid(mids);
        if (CollectionUtil.isEmpty(userRoleBOS)) {
            return null;
        }
        return userRoleBOS.stream().collect(Collectors.toMap(UserRoleBO::getUserId, userRoleBO -> userRoleBO));
    }

    @Override
    public void updateUserRole(Long userId, Long roleId) {
        userRoleMapper.updateUserRole(userId,roleId);
    }

    @Override
    public void addUserRole(UserRoleAddDTO userRoleAddDTO) {
        UserRole userRole = BeanUtil.copyProperties(userRoleAddDTO, UserRole.class, true);
        userRoleMapper.insertSelective(userRole);
    }

    @Override
    public void deleteUserRole(Long userId) {
        userRoleMapper.deleteByUid(userId);
    }
}
