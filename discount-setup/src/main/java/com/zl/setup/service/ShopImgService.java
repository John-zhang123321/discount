package com.zl.setup.service;

import com.zl.setup.dto.add.ShopImgAddDTO;
import com.zl.setup.dto.query.ShopImgQueryDTO;
import com.zl.setup.dto.update.ShopImgUpdateDTO;

import java.util.List;
import com.zl.setup.bo.ShopImgBO;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-20
*/
//@CacheConfig(cacheNames = "shopImg")
public interface ShopImgService {

    //@CacheEvict(allEntries = true)
    Long add(ShopImgAddDTO shopImgAddDTO,MultipartFile file);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(ShopImgUpdateDTO shopImgUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<ShopImgBO> getByParam(ShopImgQueryDTO shopImgQueryDTO);

}