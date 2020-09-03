package com.zl.power.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.utils.BeanUtil;
import com.zl.power.bo.PermissionBO;
import com.zl.power.bo.PermissionLittleBO;
import com.zl.power.dto.PermissionQueryDTO;
import com.zl.power.dto.add.PermissionAddDTO;
import com.zl.power.dto.update.PermissionUpdateDTO;
import com.zl.power.entity.Permission;
import com.zl.power.expection.PermissionException;
import com.zl.power.mapper.PermissionMapper;
import com.zl.power.mapper.RolePermissionMapper;
import com.zl.power.service.PermissionService;
import com.zl.power.utils.PermissionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * Created by zhangliang on 2019/5/24
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermissionMapper rolePermissionMapper;


    @Override
    public List<PermissionLittleBO> tree() {
        List<PermissionLittleBO> permissionTreeBOS = permissionMapper.tree();
        if (CollectionUtil.isEmpty(permissionTreeBOS)) {
            return permissionTreeBOS;
        }
        return PermissionUtil.createPermissionLittle(permissionTreeBOS);
    }

    @Override
    public List<PermissionBO> getByParam(PermissionQueryDTO permissionQueryDTO) {
        List<PermissionBO> permissionBOS = permissionMapper.getByParam(permissionQueryDTO);
        if (CollectionUtil.isEmpty(permissionBOS)) {
            return permissionBOS;
        }
        return PermissionUtil.createPermissionTree(permissionBOS);
    }

    @Override
    public void add(List<PermissionAddDTO> permissionAddDTOs) {
        Long pid = null;
        permissionAddDTOs.sort((o1,o2)->o2.getPid().compareTo(o1.getPid()));

        for (PermissionAddDTO permissionAddDTO : permissionAddDTOs) {
            Permission permission = BeanUtil.copyProperties(permissionAddDTO, Permission.class,true);
            if (pid == null) {
                pid = permission.getId();
            }else {
                permission.setPid(pid);
            }

            permissionMapper.insertSelective(permission);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(long id) {
        //1.判断该权限是否跟角色关联
        if(rolePermissionMapper.getCountByPermissionId(id) > 0){
            throw new PermissionException(ErrorCodeConstants.PERMISSION_DELETE_ERROR);
        }
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if (permission == null || permission.getType() == 0) {
            throw new PermissionException(ErrorCodeConstants.CHECK_PERMISSION_IS_DIR_ERROR);
        }

        if(permission.getPid() == 1){
            permissionMapper.deleteByPid(id);
        }

        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(PermissionUpdateDTO permissionUpdateDTO) {
        Permission permission = BeanUtil.copyProperties(permissionUpdateDTO, Permission.class,false);
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

}
