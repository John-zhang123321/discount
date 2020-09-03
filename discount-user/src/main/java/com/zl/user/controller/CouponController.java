package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.dto.update.CouponGeneratorDTO;
import com.zl.user.service.CouponService;
import com.zl.user.dto.add.CouponAddDTO;
import com.zl.user.dto.query.CouponQueryDTO;
import com.zl.user.dto.update.CouponUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
import java.util.List;

/**
* @author zhangliang
* @date 2019-12-26
*/
@Api(tags = "coupon接口")
@RestController
@RequestMapping("coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody CouponAddDTO couponAddDTO, @Ignore ResultObject resultObject){
        couponService.add(couponAddDTO);
        return resultObject;
    }


    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        couponService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody CouponUpdateDTO couponUpdateDTO, @Ignore ResultObject resultObject){
        couponService.updateById(couponUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody CouponQueryDTO couponQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(couponService.getByParam(couponQueryDTO));
        return resultObject;
    }

    @ApiOperation(value = "生成折扣")
    @PostMapping("/generator")
    public ResultObject generator(@Valid @RequestBody CouponGeneratorDTO couponGeneratorDTO, @Ignore ResultObject resultObject){
        resultObject.setData(couponService.generator(couponGeneratorDTO));
        return resultObject;
    }


    @ApiOperation(value = "优惠券过期")
    @GetMapping("/expiredCouponByGoodsIds")
    public void expiredCouponByGoodsIds(@RequestParam("goodsIds") List<Long> goodsIds){
        couponService.expiredCouponByGoodsIds(goodsIds);
    }

    @ApiOperation(value = "根据用户和店铺获取优惠券商品id")
    @GetMapping("/getCouponByUserIdAndShopId")
    public List<Long> getCouponByUserIdAndShopId(@RequestParam("shopId")Long shopId){
        return couponService.getCouponByUserIdAndShopId(shopId);
    }

    @ApiOperation(value = "根据商品id删除优惠券")
    @GetMapping("/deleteByGoodsId")
    public void deleteByGoodsId(@RequestParam("goodsId")Long goodsId){
        couponService.deleteByGoodsId(goodsId);
    }
}