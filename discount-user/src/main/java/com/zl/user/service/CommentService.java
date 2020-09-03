package com.zl.user.service;

import com.zl.common.entity.MyPageInfo;
import com.zl.user.dto.add.CommentAddDTO;
import com.zl.user.dto.add.CommentReplyDTO;
import com.zl.user.dto.query.CommentQueryDTO;
import com.zl.user.dto.update.CommentUpdateDTO;

import java.util.List;
import com.zl.user.bo.CommentBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-12-05
*/
//@CacheConfig(cacheNames = "comment")
public interface CommentService {

    //@CacheEvict(allEntries = true)
    void add(CommentAddDTO commentAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(CommentUpdateDTO commentUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<CommentBO> getByParam(CommentQueryDTO commentQueryDTO);

    MyPageInfo getByShopId(CommentQueryDTO commentQueryDTO);

    void reply(CommentReplyDTO commentReplyDTO);

    double getRateByShopId(Long shopId);
}