package com.zl.setup.controller;

import com.zl.common.base.ResultObject;
import com.zl.setup.dto.query.GoodsByShopQueryDTO;
import com.zl.setup.service.GoodsService;
import com.zl.setup.dto.add.GoodsAddDTO;
import com.zl.setup.dto.query.GoodsQueryDTO;
import com.zl.setup.dto.update.GoodsUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Map;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Api(tags = "goods接口")
@RestController
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody GoodsAddDTO goodsAddDTO, @Ignore ResultObject resultObject){
        goodsService.add(goodsAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        goodsService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody GoodsUpdateDTO goodsUpdateDTO, @Ignore ResultObject resultObject){
        goodsService.updateById(goodsUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody GoodsQueryDTO goodsQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(goodsService.getByParam(goodsQueryDTO));
        return resultObject;
    }

    @ApiOperation(value = "根据店铺id查询")
    @PostMapping(value = "/getByShopId")
    public ResultObject getByShopId(@Valid @RequestBody GoodsByShopQueryDTO goodsByShopQueryDTO, @Ignore ResultObject resultObject){
        resultObject.setData(goodsService.getByShopId(goodsByShopQueryDTO));
        return resultObject;
    }

    @ApiOperation(value = "根据商品id查询")
    @GetMapping(value = "/getProportionByGoodsId")
    public int getProportionByGoodsId(@RequestParam("goodsId") Long goodsId){
        return goodsService.getProportionByGoodsId(goodsId);
    }

    @ApiOperation(value = "根据商品id添加出售数量")
    @GetMapping("/addSale")
    public void addSale(@RequestParam("goodsId")Long goodsId){
        goodsService.addSale(goodsId);
    }

}