package com.zl.setup.service;

import com.zl.setup.dto.add.GoodsImgAddDTO;
import com.zl.setup.dto.query.GoodsImgQueryDTO;
import com.zl.setup.dto.update.GoodsImgUpdateDTO;

import java.util.List;
import com.zl.setup.bo.GoodsImgBO;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-20
*/
//@CacheConfig(cacheNames = "goodsImg")
public interface GoodsImgService {

    //@CacheEvict(allEntries = true)
    Long add(GoodsImgAddDTO goodsImgAddDTO, MultipartFile file);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(GoodsImgUpdateDTO goodsImgUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<GoodsImgBO> getByParam(GoodsImgQueryDTO goodsImgQueryDTO);

}