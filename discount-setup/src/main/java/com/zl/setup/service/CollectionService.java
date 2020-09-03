package com.zl.setup.service;

import com.zl.setup.dto.add.CollectionAddDTO;
import com.zl.setup.dto.query.CollectionQueryDTO;
import com.zl.setup.dto.update.CollectionUpdateDTO;

import java.util.List;
import com.zl.setup.bo.CollectionBO;
import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-20
*/
@CacheConfig(cacheNames = "collection")
public interface CollectionService {

    //@CacheEvict(allEntries = true)
    void add(CollectionAddDTO collectionAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(CollectionUpdateDTO collectionUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<CollectionBO> getByParam(CollectionQueryDTO collectionQueryDTO);

}