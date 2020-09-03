package com.zl.user.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.common.base.ResultObject;
import com.zl.user.dto.update.ShopUpdateDTO;
import com.zl.user.service.FollowService;
import com.zl.user.dto.add.FollowAddDTO;
import com.zl.user.dto.query.FollowQueryDTO;
import com.zl.user.dto.update.FollowUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.Valid;
import java.util.List;

/**
* @author zhangliang
* @date 2019-12-01
*/
@Api(tags = "follow接口")
@RestController
@RequestMapping("follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody FollowAddDTO followAddDTO, @Ignore ResultObject resultObject){
        followService.add(followAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{shopId}")
    public ResultObject deleteById(@Valid @PathVariable("shopId") Long shopId, @Ignore ResultObject resultObject){
        followService.deleteById(shopId);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody FollowUpdateDTO followUpdateDTO, @Ignore ResultObject resultObject){
        followService.updateById(followUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody FollowQueryDTO followQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(followService.getByParam(followQueryDTO));
        return resultObject;
    }

    @ApiOperation(value = "删除用户关注店铺的缓存")
    @DeleteMapping("/deleteFollowByShopId/{shopId}")
    public void deleteFollowByShopId(@PathVariable("shopId") Long shopId){
        followService.deleteFollowByShopId(shopId);
    }

    @ApiOperation(value = "获取用户是否关注该店铺")
    @GetMapping("/getFollowStatusByShopIdAndUserId/{shopId}")
    public ResultObject getFollowStatusByShopIdAndUserId(@Valid @PathVariable("shopId")long shopId,@Ignore ResultObject resultObject){
        resultObject.setData(followService.getFollowStatusByShopIdAndUserId(shopId));
        return resultObject;
    }
}