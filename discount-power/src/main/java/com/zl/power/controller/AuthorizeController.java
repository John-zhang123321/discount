package com.zl.power.controller;

import com.zl.common.base.ResultObject;
import com.zl.power.bo.AuthorizeBO;
import com.zl.power.service.AuthorizeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by zhangliang on 2019/6/6
 */
@Slf4j
@Api(tags = "授权接口")
@RestController
@RequestMapping("/authorize")
public class AuthorizeController {

    @Autowired
    AuthorizeService authorizeService;


    @ApiOperation("获取用户授权信息")
    @GetMapping(value="/getAuthorizeInfo")
    @SuppressWarnings("all")
    public AuthorizeBO getAuthorizeInfo(long uid, @ApiIgnore ResultObject resultObject) {
        long start = System.currentTimeMillis();
        AuthorizeBO authorizeInfo = authorizeService.getAuthorizeInfo(uid);
        log.info("耗时:{}",(System.currentTimeMillis() - start));
        return authorizeInfo;
    }


}
