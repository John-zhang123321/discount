package com.zl.user.service;

import com.zl.user.bo.ManagerBo;
import com.zl.user.dto.add.ManagerAddDTO;
import com.zl.user.dto.query.ManagerLoginQueryDTO;
import com.zl.user.dto.query.ManagerQueryDTO;
import com.zl.user.dto.update.ManagerUpdateDTO;

import java.util.List;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface ManagerService {
    Object login(ManagerLoginQueryDTO managerLoginQueryDTO);

    List<ManagerBo> getByParam(ManagerQueryDTO managerQueryDTO);

    void updateById(ManagerUpdateDTO managerUpdateDTO);

    void add(ManagerAddDTO managerAddDTO);

    void deleteById(Long id);

    void notice(String cityCode,String customerId);

    Object getInfo();

}
