package com.zl.setup.controller;

import com.zl.common.base.ResultObject;
import com.zl.setup.service.HotSearchService;
import com.zl.setup.dto.add.HotSearchAddDTO;
import com.zl.setup.dto.query.HotSearchQueryDTO;
import com.zl.setup.dto.update.HotSearchUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
/**
* @author zhangliang
* @date 2020-01-04
*/
@Api(tags = "hotSearch接口")
@RestController
@RequestMapping("hotSearch")
public class HotSearchController {

    @Autowired
    private HotSearchService hotSearchService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody HotSearchAddDTO hotSearchAddDTO, @Ignore ResultObject resultObject){
        hotSearchService.add(hotSearchAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        hotSearchService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody HotSearchUpdateDTO hotSearchUpdateDTO, @Ignore ResultObject resultObject){
        hotSearchService.updateById(hotSearchUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody HotSearchQueryDTO hotSearchQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(hotSearchService.getByParam(hotSearchQueryDTO)));
        return resultObject;
    }

}