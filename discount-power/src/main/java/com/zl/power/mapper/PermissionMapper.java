package com.zl.power.mapper;

import com.zl.power.bo.PermissionBO;
import com.zl.power.bo.PermissionLittleBO;
import com.zl.power.bo.PermissionSmallBO;
import com.zl.power.bo.PermissionTreeBO;
import com.zl.power.dto.PermissionQueryDTO;
import com.zl.power.entity.Permission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Set;

public interface PermissionMapper extends BaseMapper<Permission> {
//    List<PermissionVo> getByUserId(@Param("uid") String uid);

    List<PermissionSmallBO> getAll();


    List<PermissionBO> getByParam(@Param("permissionParamDTO") PermissionQueryDTO permissionQueryDTO);

    List<PermissionLittleBO> tree();

    List<PermissionBO> getPermissionsByPermissionIds(@Param("uid") Long uid);

    int getPidByPermission(@Param("id")long id);

    void deleteByPid(@Param("id")long id);

    List<PermissionBO> getByRoleIds(@Param("roleIds")List<Long> roleIds);

    List<PermissionLittleBO> getLittlePermission(@Param("permissionIds")List<Long> permissionIds);
}