package com.zl.power.mapper;

import com.zl.power.bo.UserRoleBO;
import com.zl.power.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Long> getRoleIdsByUid(@Param("uid") long uid);

    boolean getCountByRoleId(@Param("roleId")long roleId);

    List<UserRoleBO> getRoleIdByUid(@Param("mids")List<Long> mids);

    void updateUserRole(@Param("userId")Long userId,@Param("roleId") Long roleId);

    void deleteByUid(@Param("userId")Long userId);
}