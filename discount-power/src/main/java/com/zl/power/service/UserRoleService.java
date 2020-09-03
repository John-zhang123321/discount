package com.zl.power.service;

import com.zl.power.bo.RoleBO;
import com.zl.power.bo.UserRoleBO;
import com.zl.power.dto.add.RoleAddDTO;
import com.zl.power.dto.add.UserRoleAddDTO;
import com.zl.power.dto.query.RoleQueryDTO;
import com.zl.power.dto.update.RoleUpdateDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface UserRoleService {

    Map<Long, UserRoleBO> getRoleIdByUid(List<Long> mids);

    void updateUserRole(Long userId, Long roleId);

    void addUserRole(UserRoleAddDTO userRoleAddDTO);

    void deleteUserRole(Long userId);
}
