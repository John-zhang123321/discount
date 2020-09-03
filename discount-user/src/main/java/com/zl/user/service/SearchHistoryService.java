package com.zl.user.service;

import com.zl.user.dto.add.SearchHistoryAddDTO;
import com.zl.user.dto.query.SearchHistoryQueryDTO;
import com.zl.user.dto.update.SearchHistoryUpdateDTO;

import java.util.List;
import com.zl.user.bo.SearchHistoryBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2020-01-04
*/
//@CacheConfig(cacheNames = "searchHistory")
public interface SearchHistoryService {

    //@CacheEvict(allEntries = true)
    void add(SearchHistoryAddDTO searchHistoryAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(SearchHistoryUpdateDTO searchHistoryUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<SearchHistoryBO> getByParam(SearchHistoryQueryDTO searchHistoryQueryDTO);

    void deleteByUserId();

}