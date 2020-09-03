package com.zl.power.mapper;

import com.zl.power.bo.RoleBO;
import com.zl.power.dto.query.RoleQueryDTO;
import com.zl.power.entity.Role;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {

    List<RoleBO> getByParam(@Param("roleQueryDTO") RoleQueryDTO roleQueryDTO);

    List<RoleBO> getRolesByRoleIds(@Param("roleIds") List<String> roleIds);

    List<Map<String, String>> getAllRole();
}