package com.zl.power.mapper;

import com.zl.power.bo.PermissionBO;
import com.zl.power.bo.RolePermissionBO;
import com.zl.power.entity.RolePermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.ArrayList;
import java.util.List;

public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    void deleteByRoleId(@Param("roleId") long roleId);

    List<Long> getPermissionIdsByRoleIds(@Param("roleIds") List<Long> roleIds);

    int getCountByPermissionId(@Param("id")long id);

    List<RolePermissionBO> getRolePermissionsByRoleIds(@Param("roleIds")List<Long> roleIds);

}