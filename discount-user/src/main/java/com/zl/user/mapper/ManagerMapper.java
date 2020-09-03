package com.zl.user.mapper;

import com.zl.user.bo.ManagerBo;
import com.zl.user.dto.query.ManagerLoginQueryDTO;
import com.zl.user.dto.query.ManagerQueryDTO;
import com.zl.user.entity.Manager;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface ManagerMapper extends BaseMapper<Manager> {
    Manager getAccount(@Param("phone") String phone);

    Manager login(@Param("managerLoginQueryDTO") ManagerLoginQueryDTO managerLoginQueryDTO);

    List<ManagerBo> getByParam(@Param("managerQueryDTO") ManagerQueryDTO managerQueryDTO);

    List<String> getByCityCode(@Param("cityCode") String cityCode);
}