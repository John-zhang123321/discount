package com.zl.user.service;

import com.zl.common.entity.MyPageInfo;
import com.zl.user.bo.CouponGeneratorBO;
import com.zl.user.dto.add.CouponAddDTO;
import com.zl.user.dto.query.CouponQueryDTO;
import com.zl.user.dto.update.CouponGeneratorDTO;
import com.zl.user.dto.update.CouponUpdateDTO;

import java.util.List;
import com.zl.user.bo.CouponBO;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-12-26
*/
//@CacheConfig(cacheNames = "coupon")
public interface CouponService {

    //@CacheEvict(allEntries = true)
    void add(CouponAddDTO couponAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(CouponUpdateDTO couponUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    MyPageInfo getByParam(CouponQueryDTO couponQueryDTO);

    void expiredCouponByGoodsIds(List<Long> goodsIds);

    List<Long> getCouponByUserIdAndShopId(Long shopId);

    CouponGeneratorBO generator(CouponGeneratorDTO couponGeneratorDTO);

    void deleteByGoodsId(Long goodsId);
}