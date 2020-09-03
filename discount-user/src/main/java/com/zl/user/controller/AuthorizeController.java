package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.common.utils.JwtTokenUtil;
import com.zl.common.utils.RequestResponseUtil;
import com.zl.user.dto.add.LoginDTO;
import com.zl.user.service.AuthorizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* @author zhangliang
* @date 2019-11-18
*/
@Api(tags = "登陆接口")
@RestController
@RequestMapping("authorize")
public class AuthorizeController {

    @Autowired
    private AuthorizeService authorizeService;

    @ApiOperation(value = "登陆")
    @PostMapping(value = "/login")
    public ResultObject login(@Valid @RequestBody LoginDTO loginDTO, @Ignore ResultObject resultObject){
        RequestResponseUtil.setToken(JwtTokenUtil.generateToken(1l));
//        resultObject.setData(authorizeService.login(loginDTO));
        return resultObject;
    }

    @ApiOperation(value = "登陆")
    @GetMapping(value = "/login2")
    public ResultObject login2( @Ignore ResultObject resultObject){
        long userId = RequestResponseUtil.getUserId();
        System.out.println(userId);
        RequestResponseUtil.setToken(JwtTokenUtil.generateToken(1l));
//        resultObject.setData(authorizeService.login(loginDTO));
        return resultObject;
    }

    @ApiOperation(value = "获取用户类型")
    @GetMapping(value = "/getUserType")
    public ResultObject getUserType(String name,@Ignore ResultObject resultObject){
        resultObject.setData(authorizeService.getUserType());
        return resultObject;
    }
}