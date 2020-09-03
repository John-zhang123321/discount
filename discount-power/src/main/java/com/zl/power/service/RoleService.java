package com.zl.power.service;

import com.zl.power.bo.RoleBO;
import com.zl.power.dto.add.RoleAddDTO;
import com.zl.power.dto.query.RoleQueryDTO;
import com.zl.power.dto.update.RoleUpdateDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface RoleService {
    void add(RoleAddDTO roleAddDTO);

    void deleteById(long id);

    void updateById(RoleUpdateDTO roleUpdateDTO);

    List<RoleBO> getByParam(RoleQueryDTO roleQueryDTO);

    List<Map<String,String >> getAllRole();

}
