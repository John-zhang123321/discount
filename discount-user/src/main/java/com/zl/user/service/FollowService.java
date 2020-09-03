package com.zl.user.service;

import com.zl.common.entity.MyPageInfo;
import com.zl.user.dto.add.FollowAddDTO;
import com.zl.user.dto.query.FollowQueryDTO;
import com.zl.user.dto.update.FollowUpdateDTO;

import java.util.List;
import com.zl.user.bo.FollowBO;
import com.zl.user.dto.update.ShopUpdateDTO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-12-01
*/
//@CacheConfig(cacheNames = "follow")
public interface FollowService {

    //@CacheEvict(allEntries = true)
    void add(FollowAddDTO followAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(long shopId);

    //@CacheEvict(allEntries = true)
    void updateById(FollowUpdateDTO followUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    MyPageInfo getByParam(FollowQueryDTO followQueryDTO);


    void deleteFollowByShopId(Long shopId);

    boolean getFollowStatusByShopIdAndUserId(long shopId);
}