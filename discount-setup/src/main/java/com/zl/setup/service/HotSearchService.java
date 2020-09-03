package com.zl.setup.service;

import com.zl.setup.dto.add.HotSearchAddDTO;
import com.zl.setup.dto.query.HotSearchQueryDTO;
import com.zl.setup.dto.update.HotSearchUpdateDTO;

import java.util.List;
import com.zl.setup.bo.HotSearchBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2020-01-04
*/
//@CacheConfig(cacheNames = "hotSearch")
public interface HotSearchService {

    //@CacheEvict(allEntries = true)
    void add(HotSearchAddDTO hotSearchAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(HotSearchUpdateDTO hotSearchUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<HotSearchBO> getByParam(HotSearchQueryDTO hotSearchQueryDTO);

}