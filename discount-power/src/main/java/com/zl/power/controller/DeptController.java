package com.zl.power.controller;

import com.zl.common.base.ResultObject;
import com.zl.power.service.DeptService;
import com.zl.power.dto.add.DeptAddDTO;
import com.zl.power.dto.query.DeptQueryDTO;
import com.zl.power.dto.update.DeptUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
/**
* @author zhangliang
* @date 2019-11-02
*/
@Api(tags = "dept管理")
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation(value = "新增")
    @PostMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody DeptAddDTO deptAddDTO, @Ignore ResultObject resultObject){
        deptService.add(deptAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        deptService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody DeptUpdateDTO deptUpdateDTO, @Ignore ResultObject resultObject){
        deptService.updateById(deptUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody DeptQueryDTO deptQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(deptService.getByParam(deptQueryDTO)));
        return resultObject;
    }

}