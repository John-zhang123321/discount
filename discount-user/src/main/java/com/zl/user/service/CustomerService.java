package com.zl.user.service;

import com.zl.user.dto.add.LoginDTO;
import com.zl.user.dto.query.CustomerQueryDTO;
import com.zl.user.dto.update.CustomerUpdateDTO;

import java.util.List;
import com.zl.user.bo.CustomerBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-18
*/
//@CacheConfig(cacheNames = "customer")
public interface CustomerService {

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(CustomerUpdateDTO customerUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<CustomerBO> getByParam(CustomerQueryDTO customerQueryDTO);

    String getOpenid(Long customerId);
}