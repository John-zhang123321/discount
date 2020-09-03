package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.dto.WxLoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangliang on 2019/6/6
 */
@Api(tags = "user接口")
@RestController
@RequestMapping("/wxUser")
public class WxUserController {

//    @Autowired
//    MerchantService userService;

    @ApiOperation("用户登录")
    @RequestMapping(value="/login")
    @SuppressWarnings("all")
    public ResultObject login(@RequestBody WxLoginDto wxLoginDto, ResultObject resultObject) {
//        resultObject.setData(userService.login(wxLoginDto));
        return resultObject;
    }
}
