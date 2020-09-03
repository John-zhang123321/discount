package com.zl.power.service.impl;

import com.zl.common.utils.BeanUtil;
import com.zl.power.entity.Dept;
import com.zl.power.service.DeptService;
import com.zl.power.dto.add.DeptAddDTO;
import com.zl.power.dto.query.DeptQueryDTO;
import com.zl.power.dto.update.DeptUpdateDTO;
import com.zl.power.mapper.DeptMapper;
import com.zl.power.bo.DeptBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.github.pagehelper.PageHelper;


/**
* @author zhangliang
* @date 2019-11-02
*/
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public void add(DeptAddDTO deptAddDTO) {
        Dept dept = BeanUtil.copyProperties(deptAddDTO, Dept.class,true);
        deptMapper.insertSelective(dept);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        deptMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(DeptUpdateDTO deptUpdateDTO) {
        Dept dept = BeanUtil.copyProperties(deptUpdateDTO, Dept.class,false);
        deptMapper.updateByPrimaryKeySelective(dept);
    }


    @Override
    public List<DeptBO> getByParam(DeptQueryDTO deptQueryDTO){
        PageHelper.startPage(deptQueryDTO.getPageNum(), deptQueryDTO.getPageSize());
        return deptMapper.getByParam(deptQueryDTO);
    }

}