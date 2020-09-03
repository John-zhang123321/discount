package com.zl.power.mapper;

import com.zl.power.dto.query.DeptQueryDTO;
import com.zl.power.entity.Dept;
import com.zl.power.bo.DeptBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-11-02
*/
public interface DeptMapper extends BaseMapper<Dept> {

    List<DeptBO> getByParam(@Param("deptQueryDTO") DeptQueryDTO deptQueryDTO);
}