package com.zl.setup.controller;

import com.zl.common.base.ResultObject;
import com.zl.setup.dto.add.ShopImgAddDTO;
import com.zl.setup.service.GoodsImgService;
import com.zl.setup.dto.add.GoodsImgAddDTO;
import com.zl.setup.dto.query.GoodsImgQueryDTO;
import com.zl.setup.dto.update.GoodsImgUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
* @author zhangliang
* @date 2019-11-20
*/
@Api(tags = "goodsImg接口")
@RestController
@RequestMapping("goodsImg")
public class GoodsImgController {

    @Autowired
    private GoodsImgService goodsImgService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public ResultObject add(GoodsImgAddDTO goodsImgAddDTO, @RequestParam(value = "file",required = true) MultipartFile file, @Ignore ResultObject resultObject){
        resultObject.setData(goodsImgService.add(goodsImgAddDTO,file));
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        goodsImgService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody GoodsImgUpdateDTO goodsImgUpdateDTO, @Ignore ResultObject resultObject){
        goodsImgService.updateById(goodsImgUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody GoodsImgQueryDTO goodsImgQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(goodsImgService.getByParam(goodsImgQueryDTO)));
        return resultObject;
    }

}