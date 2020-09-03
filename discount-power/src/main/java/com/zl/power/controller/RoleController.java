package com.zl.power.controller;

import com.github.pagehelper.PageInfo;
import com.zl.common.base.ResultObject;
import com.zl.power.dto.add.RoleAddDTO;
import com.zl.power.dto.query.RoleQueryDTO;
import com.zl.power.dto.update.RoleUpdateDTO;
import com.zl.power.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Slf4j
@RestController
@RequestMapping("role")
@Valid
public class RoleController {

    @Autowired
    RoleService roleService;

    @ApiOperation("新增")
    @PutMapping(value = "/add")
    public ResultObject add(@RequestBody RoleAddDTO roleAddDTO, @ApiIgnore ResultObject resultObject){
        roleService.add(roleAddDTO);
        return resultObject;
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@NotNull @PathVariable("id") Long id, @ApiIgnore ResultObject resultObject){
        roleService.deleteById(id);
        return resultObject;
    }

    @ApiOperation("更新")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@RequestBody RoleUpdateDTO roleUpdateDTO, @ApiIgnore ResultObject resultObject){
        roleService.updateById(roleUpdateDTO);
        return resultObject;
    }


    @ApiOperation("查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@RequestBody RoleQueryDTO roleQueryDTO, @ApiIgnore ResultObject resultObject){
        resultObject.setData(new PageInfo(roleService.getByParam(roleQueryDTO)));
        return resultObject;
    }

    @ApiOperation("获取所有角色")
    @PostMapping(value = "/getAllRole")
    public ResultObject getAllRole(@ApiIgnore ResultObject resultObject){
        resultObject.setData(roleService.getAllRole());
        return resultObject;
    }
}
