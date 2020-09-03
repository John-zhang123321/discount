package com.zl.power.controller;

import com.zl.common.base.ResultObject;
import com.zl.power.dto.RolePermissionParamDTO;
import com.zl.power.service.RolePermissionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("rolePermission")
public class RolePermissionController {

    @Autowired
    RolePermissionService rolePermissionService;


    @ApiOperation("修改或新增")
    @PostMapping(value = "/updateOrAdd")
    public ResultObject getByParam(@Valid  @RequestBody RolePermissionParamDTO rolePermissionParamDTO, @ApiIgnore ResultObject resultObject){
        rolePermissionService.updateOrAdd(rolePermissionParamDTO);
        return resultObject;
    }

}
