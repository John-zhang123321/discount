package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.dto.add.FootprintAddDTO;
import com.zl.user.dto.query.FootprintQueryDTO;
import com.zl.user.service.FootprintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by zhangliang on 2019/6/6
 */
@Api(tags = "足迹接口")
@RestController
@RequestMapping("/footprint")
public class FootprintController {

    @Autowired
    FootprintService footprintService;


    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody FootprintAddDTO footprintAddDTO, @Ignore ResultObject resultObject){
        footprintService.add(footprintAddDTO);
        return resultObject;
    }
//
//    @ApiOperation(value = "删除")
//    @DeleteMapping(value = "/deleteById/{id}")
//    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
//        footprintService.deleteById(id);
//        return resultObject;
//    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody FootprintQueryDTO footprintQueryDTO, @Ignore ResultObject resultObject){
        resultObject.setData(footprintService.getByParam(footprintQueryDTO));
        return resultObject;
    }

}
