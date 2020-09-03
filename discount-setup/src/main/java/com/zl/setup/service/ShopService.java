package com.zl.setup.service;

import com.zl.common.entity.MyPageInfo;
import com.zl.setup.bo.Shop4FollowBO;
import com.zl.setup.bo.ShopLittleBO;
import com.zl.setup.dto.add.ShopAddDTO;
import com.zl.setup.dto.query.ShopQuery4CategoryDTO;
import com.zl.setup.dto.query.ShopQuery4FollowDTO;
import com.zl.setup.dto.query.ShopQueryDTO;
import com.zl.setup.dto.query.ShopSearchDTO;
import com.zl.setup.dto.update.ShopAuditDTO;
import com.zl.setup.dto.update.ShopUpdateDTO;
import com.zl.setup.bo.ShopBO;

import java.util.List;
import java.util.Map;
//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-20
*/
//@CacheConfig(cacheNames = "shop")
public interface ShopService {

    //@CacheEvict(allEntries = true)
    void add(ShopAddDTO shopAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(ShopUpdateDTO shopUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    MyPageInfo getByParam(ShopQueryDTO shopQueryDTO);

    ShopBO getShopInfo();

    List<Shop4FollowBO> getByShopId(List<ShopQuery4FollowDTO> shopQuery4FollowDTOS);

    String getShopStatus();

    MyPageInfo getShopsByCategoryId(ShopQuery4CategoryDTO shopQuery4CategoryDTO);

    ShopLittleBO getById(Long shopId);

    void audit(ShopAuditDTO shopAuditDTO);

    void reAudit(ShopUpdateDTO shopUpdateDTO);

    Long getMerchantIdByShopId(Long shopId);

    List<ShopLittleBO> getByIds(List<Long> shopIds);

    List<ShopBO> getByParam4Web(ShopQueryDTO shopQueryDTO);

    void updateShopRateByShopId(Long shopId,double rate);

    List<ShopLittleBO> search(ShopSearchDTO shopSearchDTO);
}