package com.zl.power.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.utils.BeanUtil;
import com.zl.power.bo.*;
import com.zl.power.dto.add.RoleAddDTO;
import com.zl.power.dto.query.RoleQueryDTO;
import com.zl.power.dto.update.RoleUpdateDTO;
import com.zl.power.entity.Role;
import com.zl.power.expection.RoleException;
import com.zl.power.mapper.PermissionMapper;
import com.zl.power.mapper.RoleMapper;
import com.zl.power.mapper.RolePermissionMapper;
import com.zl.power.mapper.UserRoleMapper;
import com.zl.power.service.RoleService;
import com.zl.power.utils.PermissionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;

    @Override
    public void add(RoleAddDTO roleAddDTO) {
        Role role = BeanUtil.copyProperties(roleAddDTO, Role.class,true);
        roleMapper.insertSelective(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(long id) {
        //1.判断该角色是否跟用户关联
        if(userRoleMapper.getCountByRoleId(id)){
            throw new RoleException(ErrorCodeConstants.ROLE_DELETE_ERROR);
        }

        roleMapper.deleteByPrimaryKey(id);

        rolePermissionMapper.deleteByRoleId(id);
    }

    @Override
    public void updateById(RoleUpdateDTO roleUpdateDTO) {
        Role role = BeanUtil.copyProperties(roleUpdateDTO, Role.class,false);
        roleMapper.updateByPrimaryKeySelective(role);
    }


    @Override
    public List<RoleBO> getByParam(RoleQueryDTO roleQueryDTO) {
         PageHelper.startPage(roleQueryDTO.getPageNum(),roleQueryDTO.getPageSize());
        List<RoleBO> roleBOS = roleMapper.getByParam(roleQueryDTO);
        if (CollectionUtil.isEmpty(roleBOS)) {
            return roleBOS;
        }
        List<Long> roleIds = roleBOS.stream().map(roleBO -> roleBO.getId()).collect(Collectors.toList());

        List<RolePermissionBO> rolePermissionBOS = rolePermissionMapper.getRolePermissionsByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(rolePermissionBOS)) {
            return roleBOS;
        }
        Map<Long, List<PermissionLittleBO>> rolePermissionLittleBOMap = rolePermissionBOS.stream().collect(Collectors.toMap(RolePermissionBO::getRoleId,rolePermissionBO -> rolePermissionBO.getPermissionLittleBOS()));
        for (RoleBO roleBO : roleBOS) {
            List<PermissionLittleBO> permissionLittleBOS = rolePermissionLittleBOMap.get(roleBO.getId());
            if (!CollectionUtil.isEmpty(permissionLittleBOS)) {
                roleBO.setPermissionLittleBOS(PermissionUtil.createPermissionLittle(permissionLittleBOS));
            }
        }
        return roleBOS;
    }

    @Override
    public List<Map<String, String>> getAllRole() {
        return roleMapper.getAllRole();
    }

}
