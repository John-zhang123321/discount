package com.zl.setup.mapper;

import com.zl.setup.bo.Shop4FollowBO;
import com.zl.setup.bo.ShopBO;
import com.zl.setup.bo.ShopLittleBO;
import com.zl.setup.dto.query.ShopQueryDTO;
import com.zl.setup.entity.Shop;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
/**
* @author zhangliang
* @date 2019-11-20
*/
public interface ShopMapper extends BaseMapper<Shop> {

    List<ShopLittleBO> getByParam(@Param("shopQueryDTO") ShopQueryDTO shopQueryDTO);

    List<Shop4FollowBO> getByIdList(@Param("ids") List<Long> ids);

    boolean hasShop4Category(@Param("categoryId")Long categoryId);

    ShopBO getShopInfo(@Param("wxToken") Long wxToken);

    Long getIdByUserId(@Param("userId")Long userId);

    Boolean getStatusByUserId(@Param("userId")long userId);

    List<ShopLittleBO> getShopsByCategoryId(@Param("categoryId")Long categoryId);

    ShopLittleBO getById(@Param("shopId")Long shopId);

    Long getMerchantIdByShopId(@Param("shopId")Long shopId);

    List<ShopLittleBO> getByIds(@Param("shopIds")List<Long> shopIds);

    List<ShopBO> getByParam4Web(@Param("shopQueryDTO")ShopQueryDTO shopQueryDTO);


}