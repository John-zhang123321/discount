package com.zl.setup.controller;

import com.zl.common.base.ResultObject;
import com.zl.setup.service.ShopImgService;
import com.zl.setup.dto.add.ShopImgAddDTO;
import com.zl.setup.dto.query.ShopImgQueryDTO;
import com.zl.setup.dto.update.ShopImgUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
/**
* @author zhangliang
* @date 2019-11-20
*/
@Api(tags = "shopImg接口")
@RestController
@RequestMapping("shopImg")
public class ShopImgController {

    @Autowired
    private ShopImgService shopImgService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public ResultObject add(ShopImgAddDTO shopImgAddDTO,@RequestParam(value = "file",required = true) MultipartFile file, @Ignore ResultObject resultObject){
        resultObject.setData(shopImgService.add(shopImgAddDTO,file));
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        shopImgService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody ShopImgUpdateDTO shopImgUpdateDTO, @Ignore ResultObject resultObject){
        shopImgService.updateById(shopImgUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody ShopImgQueryDTO shopImgQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(shopImgService.getByParam(shopImgQueryDTO)));
        return resultObject;
    }

}