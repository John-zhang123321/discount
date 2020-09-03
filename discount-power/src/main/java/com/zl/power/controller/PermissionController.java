package com.zl.power.controller;

import com.zl.common.base.ResultObject;
import com.zl.power.dto.PermissionQueryDTO;
import com.zl.power.dto.add.PermissionAddDTO;
import com.zl.power.dto.update.PermissionUpdateDTO;
import com.zl.power.service.PermissionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;


@Slf4j
@Valid
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @ApiOperation("新增")
    @PostMapping(value = "/add")
    public ResultObject add(@RequestBody Map<String,List<PermissionAddDTO>> map, @ApiIgnore ResultObject resultObject){
        permissionService.add(map.get("key"));
        return resultObject;
    }

    @ApiOperation("删除权限")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@PathVariable("id") Long id, @ApiIgnore ResultObject resultObject){
        permissionService.deleteById(id);
        return resultObject;
    }

    @ApiOperation("更新")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@RequestBody PermissionUpdateDTO permissionUpdateDTO, @ApiIgnore ResultObject resultObject){
        permissionService.updateById(permissionUpdateDTO);
        return resultObject;
    }


    @ApiOperation("查询所有权限")
    @GetMapping(value = "/tree")
    public ResultObject tree(@ApiIgnore ResultObject resultObject){
        resultObject.setData(permissionService.tree());
        return resultObject;
    }


    @ApiOperation("分页查询权限")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@RequestBody PermissionQueryDTO permissionQueryDTO, @ApiIgnore ResultObject resultObject){
        resultObject.setData(permissionService.getByParam(permissionQueryDTO));
        return resultObject;
    }

}
