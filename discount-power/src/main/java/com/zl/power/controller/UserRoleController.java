package com.zl.power.controller;

import com.github.pagehelper.PageInfo;
import com.zl.common.base.ResultObject;
import com.zl.power.bo.UserRoleBO;
import com.zl.power.dto.add.RoleAddDTO;
import com.zl.power.dto.add.UserRoleAddDTO;
import com.zl.power.dto.query.RoleQueryDTO;
import com.zl.power.dto.update.RoleUpdateDTO;
import com.zl.power.service.RoleService;
import com.zl.power.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("userRole")
@Valid
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @ApiOperation("根据用户id获取角色id")
    @GetMapping(value = "/getRoleIdByUid")
    public Map<Long, UserRoleBO> add(@RequestParam("mids") List<Long> mids){
        return userRoleService.getRoleIdByUid(mids);
    }

    @ApiOperation("更新用户角色")
    @GetMapping(value = "/updateUserRole")
    public void updateUserRole(@RequestParam("userId") Long userId,@RequestParam("roleId") Long roleId){
        userRoleService.updateUserRole(userId,roleId);
    }

    @ApiOperation("添加用户角色")
    @PostMapping(value = "/addUserRole")
    public void addUserRole(UserRoleAddDTO userRoleAddDTO){
        userRoleService.addUserRole(userRoleAddDTO);
    }

    @ApiOperation("删除用户角色")
    @GetMapping(value = "/deleteUserRole")
    public void deleteUserRole(@RequestParam("userId") Long userId){
        userRoleService.deleteUserRole(userId);
    }
}
