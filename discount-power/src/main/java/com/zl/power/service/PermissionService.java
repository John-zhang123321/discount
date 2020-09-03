package com.zl.power.service;

import com.zl.power.bo.PermissionBO;
import com.zl.power.bo.PermissionLittleBO;
import com.zl.power.bo.PermissionSmallBO;
import com.zl.power.bo.PermissionTreeBO;
import com.zl.power.dto.PermissionQueryDTO;
import com.zl.power.dto.add.PermissionAddDTO;
import com.zl.power.dto.add.RoleAddDTO;
import com.zl.power.dto.update.PermissionUpdateDTO;
import com.zl.power.dto.update.RoleUpdateDTO;
import com.zl.power.entity.Permission;

import java.util.List;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface PermissionService {
    List<PermissionLittleBO> tree();

    List<PermissionBO> getByParam(PermissionQueryDTO permissionQueryDTO);

    void add(List<PermissionAddDTO> permissionAddDTOs);

    void deleteById(long id);

    void updateById(PermissionUpdateDTO permissionUpdateDTO);

}
