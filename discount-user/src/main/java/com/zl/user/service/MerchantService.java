package com.zl.user.service;

import com.zl.user.dto.add.MerchantAddDTO;
import com.zl.user.dto.query.MerchantLoginQueryDTO;
import com.zl.user.dto.query.MerchantQueryDTO;
import com.zl.user.dto.update.MerchantUpdateDTO;

import java.util.List;
import com.zl.user.bo.MerchantBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-15
*/
//@CacheConfig(cacheNames = "merchant")
public interface MerchantService {

    //@CacheEvict(allEntries = true)
    void add(MerchantAddDTO merchantAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(MerchantUpdateDTO merchantUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<MerchantBO> getByParam(MerchantQueryDTO merchantQueryDTO);

    Object login(MerchantLoginQueryDTO merchantLoginQueryDTO);
}