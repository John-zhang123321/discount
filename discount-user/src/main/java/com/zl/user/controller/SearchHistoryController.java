package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.service.SearchHistoryService;
import com.zl.user.dto.add.SearchHistoryAddDTO;
import com.zl.user.dto.query.SearchHistoryQueryDTO;
import com.zl.user.dto.update.SearchHistoryUpdateDTO;
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
@Api(tags = "searchHistory接口")
@RestController
@RequestMapping("searchHistory")
public class SearchHistoryController {

    @Autowired
    private SearchHistoryService searchHistoryService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@RequestBody SearchHistoryAddDTO searchHistoryAddDTO, @Ignore ResultObject resultObject){
        searchHistoryService.add(searchHistoryAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        searchHistoryService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "删除所有")
    @DeleteMapping(value = "/deleteAll")
    public ResultObject deleteAll(@Ignore ResultObject resultObject){
        searchHistoryService.deleteByUserId();
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody SearchHistoryUpdateDTO searchHistoryUpdateDTO, @Ignore ResultObject resultObject){
        searchHistoryService.updateById(searchHistoryUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody SearchHistoryQueryDTO searchHistoryQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(searchHistoryService.getByParam(searchHistoryQueryDTO)));
        return resultObject;
    }

}