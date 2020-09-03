package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.dto.query.MerchantLoginQueryDTO;
import com.zl.user.service.MerchantService;
import com.zl.user.dto.add.MerchantAddDTO;
import com.zl.user.dto.query.MerchantQueryDTO;
import com.zl.user.dto.update.MerchantUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
/**
* @author zhangliang
* @date 2019-11-15
*/
@Api(tags = "merchant接口")
@RestController
@RequestMapping("merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @ApiOperation(value = "登陆")
    @PostMapping(value = "/login")
    public ResultObject login(@Valid @RequestBody MerchantLoginQueryDTO merchantLoginQueryDTO, @Ignore ResultObject resultObject){
        resultObject.setData(merchantService.login(merchantLoginQueryDTO));
        return resultObject;
    }

    @ApiOperation(value = "注册")
    @PutMapping(value = "/register")
    public void register(@Valid @RequestBody MerchantAddDTO merchantAddDTO, @Ignore ResultObject resultObject){
        merchantService.add(merchantAddDTO);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        merchantService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody MerchantUpdateDTO merchantUpdateDTO, @Ignore ResultObject resultObject){
        merchantService.updateById(merchantUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody MerchantQueryDTO merchantQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(merchantService.getByParam(merchantQueryDTO)));
        return resultObject;
    }

}