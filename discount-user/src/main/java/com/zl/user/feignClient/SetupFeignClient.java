package com.zl.user.feignClient;

import com.zl.common.config.FeignTokenInterceptor;
import com.zl.user.bo.FollowBO;
import com.zl.user.bo.FollowLittleBO;
import com.zl.user.bo.ShopLittleBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by zhangliang on 2019/8/21
 */
@FeignClient(value = "setup",configuration = {FeignTokenInterceptor.class})
public interface SetupFeignClient {

    @PostMapping("/shop/getByShopId")
    List<FollowBO> getByShopId(@RequestBody List<FollowLittleBO> followLittleBOS);

    @GetMapping("/shop/getById")
    ShopLittleBO getById(@RequestParam("shopId") long shopId);

    @GetMapping("/goods/getProportionByGoodsId")
    int getProportionByGoodsId(@RequestParam("goodsId") long goodsId);

    @GetMapping("/shop/getMerchantIdByShopId")
    Long getMerchantIdByShopId(@RequestParam("shopId") Long shopId);

    @GetMapping("/shop/getByIds")
    List<ShopLittleBO> getByIds(@RequestParam("shopIds") List<Long> shopIds);

    @GetMapping("/goods/addSale")
    void addSale(@RequestParam("goodsId")Long goodsId);

    @GetMapping("/shop/updateShopRateByShopId")
    void updateShopRateByShopId(@RequestParam("shopId")Long shopId,@RequestParam("rate")double rate);
}
