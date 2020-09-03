package com.zl.setup.controller;

import com.github.pagehelper.PageInfo;
import com.zl.common.base.ResultObject;
import com.zl.setup.bo.Shop4FollowBO;
import com.zl.setup.bo.ShopLittleBO;
import com.zl.setup.dto.add.ShopAddDTO;
import com.zl.setup.dto.query.ShopQuery4CategoryDTO;
import com.zl.setup.dto.query.ShopQuery4FollowDTO;
import com.zl.setup.dto.query.ShopQueryDTO;
import com.zl.setup.dto.query.ShopSearchDTO;
import com.zl.setup.dto.update.ShopAuditDTO;
import com.zl.setup.dto.update.ShopUpdateDTO;
import com.zl.setup.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Api(tags = "shop接口")
@RestController
@RequestMapping("shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody ShopAddDTO shopAddDTO, @Ignore ResultObject resultObject){
        shopService.add(shopAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        shopService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody ShopUpdateDTO shopUpdateDTO, @Ignore ResultObject resultObject){
        shopService.updateById(shopUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/web/getByParam")
    public ResultObject getByParam4Web(@Valid @RequestBody ShopQueryDTO shopQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo<>(shopService.getByParam4Web(shopQueryDTO)));
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody ShopQueryDTO shopQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(shopService.getByParam(shopQueryDTO));
        return resultObject;
    }

    @ApiOperation(value = "根据id查询")
    @PostMapping(value = "/getByShopId")
    public List<Shop4FollowBO> getByShopId(@RequestBody List<ShopQuery4FollowDTO> shopQuery4FollowDTOS){
        return shopService.getByShopId(shopQuery4FollowDTOS);
    }

    @ApiOperation("获取商户店铺信息")
    @GetMapping(value="/getShopInfo")
    @SuppressWarnings("all")
    public ResultObject getShopInfo(@ApiIgnore ResultObject resultObject) {
        resultObject.setData(shopService.getShopInfo());
        return resultObject;
    }

    @ApiOperation("获取商户店铺审核状态")
    @GetMapping(value="/getShopStatus")
    @SuppressWarnings("all")
    public ResultObject getShopStatus(@ApiIgnore ResultObject resultObject) {
        resultObject.setData(shopService.getShopStatus());
        return resultObject;
    }

    @ApiOperation("根据分类id获取店铺")
    @PostMapping(value="/getShopsByCategoryId")
    @SuppressWarnings("all")
    public ResultObject getShopsByCategoryId(@Valid @RequestBody ShopQuery4CategoryDTO shopQuery4CategoryDTO, @ApiIgnore ResultObject resultObject) {
        resultObject.setData(shopService.getShopsByCategoryId(shopQuery4CategoryDTO));
        return resultObject;
    }

    @ApiOperation("管理员审核店铺")
    @PostMapping(value="/audit")
    @SuppressWarnings("all")
    public ResultObject audit(@Valid @RequestBody ShopAuditDTO shopAuditDTO, @Ignore ResultObject resultObject) {
        shopService.audit(shopAuditDTO);
        return resultObject;
    }

    @ApiOperation("重新审核")
    @PostMapping(value="/reAudit")
    @SuppressWarnings("all")
    public ResultObject reAudit(@Valid @RequestBody ShopUpdateDTO shopUpdateDTO,@Ignore ResultObject resultObject) {
        shopService.reAudit(shopUpdateDTO);
        return resultObject;
    }

    @ApiOperation("根据id获取店铺")
    @GetMapping("/getById")
    public ShopLittleBO getById(@RequestParam("shopId") Long shopId){
        return shopService.getById(shopId);
    }

    @ApiOperation("根据id获取店铺")
    @GetMapping("/getByIds")
    public List<ShopLittleBO> getByIds(@RequestParam("shopIds") List<Long> shopIds){
        return shopService.getByIds(shopIds);
    }

    @ApiOperation("更新店铺评论")
    @GetMapping("/updateShopRateByShopId")
    public void updateShopRateByShopId(@RequestParam("shopId")Long shopId,@RequestParam("rate")double rate){
        shopService.updateShopRateByShopId(shopId,rate);
    }
    @ApiOperation("获取店铺所有人")
    @GetMapping("/getMerchantIdByShopId")
    public Long getMerchantIdByShopId(@RequestParam("shopId") Long shopId){
        return shopService.getMerchantIdByShopId(shopId);
    }

    @ApiOperation(value = "模糊搜索")
    @PostMapping("/search")
    public ResultObject search(@Valid @RequestBody ShopSearchDTO shopSearchDTO, @Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo<>(shopService.search(shopSearchDTO)));
        return resultObject;
    }
}