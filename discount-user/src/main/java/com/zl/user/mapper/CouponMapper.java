package com.zl.user.mapper;

import com.zl.user.bo.CouponUserBO;
import com.zl.user.dto.query.CouponQueryDTO;
import com.zl.user.entity.Coupon;
import com.zl.user.bo.CouponBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2019-12-26
*/
public interface CouponMapper extends BaseMapper<Coupon> {

    List<CouponBO> getByParam(@Param("couponQueryDTO") CouponQueryDTO couponQueryDTO);

    boolean getExistByGoodsIdAndUserId(@Param("goodsId")Long goodsId, @Param("userId")Long userId);

    int expiredCouponByGoodsIds(@Param("goodsIds")List<Long> goodsIds);

    List<Long> getCouponByUserIdAndShopId(@Param("userId")long userId, @Param("shopId")Long shopId);

    List<CouponBO> getByGoodsIds(@Param("goodsIds")List<Long> goodsIds);
}