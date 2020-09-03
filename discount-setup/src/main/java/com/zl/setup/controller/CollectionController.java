package com.zl.setup.controller;

import com.zl.common.base.ResultObject;
import com.zl.setup.service.CollectionService;
import com.zl.setup.dto.add.CollectionAddDTO;
import com.zl.setup.dto.query.CollectionQueryDTO;
import com.zl.setup.dto.update.CollectionUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
/**
* @author zhangliang
* @date 2019-11-20
*/
@Api(tags = "collection接口")
@RestController
@RequestMapping("collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody CollectionAddDTO collectionAddDTO, @Ignore ResultObject resultObject){
        collectionService.add(collectionAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        collectionService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody CollectionUpdateDTO collectionUpdateDTO, @Ignore ResultObject resultObject){
        collectionService.updateById(collectionUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody CollectionQueryDTO collectionQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(collectionService.getByParam(collectionQueryDTO)));
        return resultObject;
    }

}