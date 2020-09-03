package com.zl.user.service;

import com.zl.user.dto.add.FeedbackAddDTO;
import com.zl.user.dto.query.FeedbackQueryDTO;
import com.zl.user.dto.update.FeedbackUpdateDTO;

import java.util.List;
import com.zl.user.bo.FeedbackBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-12-01
*/
//@CacheConfig(cacheNames = "feedback")
public interface FeedbackService {

    //@CacheEvict(allEntries = true)
    void add(FeedbackAddDTO feedbackAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(FeedbackUpdateDTO feedbackUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    List<FeedbackBO> getByParam(FeedbackQueryDTO feedbackQueryDTO);

}