package com.zl.setup.service;

import com.zl.common.entity.MyPageInfo;
import com.zl.setup.dto.add.GoodsAddDTO;
import com.zl.setup.dto.query.GoodsByShopQueryDTO;
import com.zl.setup.dto.query.GoodsQueryDTO;
import com.zl.setup.dto.query.ShopQueryDTO;
import com.zl.setup.dto.update.GoodsUpdateDTO;
import com.zl.setup.entity.Shop;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

//import org.springframework.cache.annotation.CacheConfig;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;

/**
* @author zhangliang
* @date 2019-11-20
*/
//@CacheConfig(cacheNames = "goods")
public interface GoodsService {

    //@CacheEvict(allEntries = true)
    void add(GoodsAddDTO goodsAddDTO);

    //@CacheEvict(allEntries = true)
    void deleteById(Long id);

    //@CacheEvict(allEntries = true)
    void updateById(GoodsUpdateDTO goodsUpdateDTO);

    //@Cacheable(keyGenerator = "keyGenerator")
    MyPageInfo getByParam(GoodsQueryDTO goodsQueryDTO);

    MyPageInfo getByShopId(GoodsByShopQueryDTO goodsByShopQueryDTO);

    int getProportionByGoodsId(Long goodsId);

    void addSale(Long goodsId);


}