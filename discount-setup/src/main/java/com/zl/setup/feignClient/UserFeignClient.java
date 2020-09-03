package com.zl.setup.feignClient;

import com.zl.common.config.FeignTokenInterceptor;
import com.zl.setup.bo.ReviewQueryBO;
import com.zl.setup.dto.add.MerchantAddDTO;
import com.zl.setup.dto.update.ShopUpdateDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhangliang on 2019/8/21
 */
@FeignClient(value = "user",configuration = {FeignTokenInterceptor.class})
public interface UserFeignClient {

    @PutMapping("/merchant/register")
    void register(MerchantAddDTO merchantAddDTO);

    @GetMapping("/review/getStatusByShopIds")
    List<ReviewQueryBO> getStatusByShopIds(@RequestParam("shopIds") List<Long> shopIds);

    @GetMapping("/review/addReview")
    void addReview(@RequestParam("shopId") Long shopId);

    @DeleteMapping("/follow/deleteFollowByShopId/{shopId}")
    void deleteFollowByShopId(@RequestParam("shopId") Long shopId);

    @GetMapping("/follow/getFollowStatusByShopIdAndUserId")
    boolean getFollowStatusByShopIdAndUserId(@RequestParam("shopId")long shopId, @RequestParam("userId")long userId);

    @GetMapping("/coupon/expiredCouponByGoodsIds")
    void expiredCouponByGoodsIds(@RequestParam("goodsIds")List<Long> goodsIds);

    @GetMapping("/coupon/getCouponByUserIdAndShopId")
    List<Long> getCouponByUserIdAndShopId(@RequestParam("shopId")Long shopId);

    @GetMapping("/coupon/deleteByGoodsId")
    void deleteByGoodsId(@RequestParam("goodsId")Long goodsId);

    @GetMapping(value="/manager/notice")
    void noticeManager(@RequestParam("cityCode") String cityCode,@RequestParam("customerId")Long customerId);

    @GetMapping(value="/customer/getOpenid")
    String getOpenid(@RequestParam("customerId")Long customerId );
}
