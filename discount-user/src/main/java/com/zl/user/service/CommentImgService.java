package com.zl.user.service;

import com.zl.user.dto.add.CommentImgAddDTO;
import com.zl.user.dto.query.CommentImgQueryDTO;
import com.zl.user.dto.update.CommentImgUpdateDTO;

import java.util.List;
import com.zl.user.bo.CommentImgBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-12-05
*/
//@CacheConfig(cacheNames = "commentImg")
public interface CommentImgService {

    //@CacheEvict(allEntries = true)
    void add(CommentImgAddDTO commentImgAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(CommentImgUpdateDTO commentImgUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<CommentImgBO> getByParam(CommentImgQueryDTO commentImgQueryDTO);

}