package com.zl.power.service;

import com.zl.power.dto.add.DeptAddDTO;
import com.zl.power.dto.query.DeptQueryDTO;
import com.zl.power.dto.update.DeptUpdateDTO;

import java.util.List;
import com.zl.power.bo.DeptBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-02
*/
//@CacheConfig(cacheNames = "dept")
public interface DeptService {

    //@CacheEvict(allEntries = true)
    void add(DeptAddDTO deptAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(DeptUpdateDTO deptUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<DeptBO> getByParam(DeptQueryDTO deptQueryDTO);

}