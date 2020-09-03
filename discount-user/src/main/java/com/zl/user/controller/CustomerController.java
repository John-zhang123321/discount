package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.service.CustomerService;
import com.zl.user.dto.add.LoginDTO;
import com.zl.user.dto.query.CustomerQueryDTO;
import com.zl.user.dto.update.CustomerUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
/**
* @author zhangliang
* @date 2019-11-18
*/
@Api(tags = "customer接口")
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        customerService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PutMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody CustomerUpdateDTO customerUpdateDTO, @Ignore ResultObject resultObject){
        customerService.updateById(customerUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody CustomerQueryDTO customerQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(customerService.getByParam(customerQueryDTO)));
        return resultObject;
    }
    @ApiOperation(value = "获取openid")
    @GetMapping(value="/getOpenid")
    public String getOpenid(@RequestParam("customerId")Long customerId){
        return customerService.getOpenid(customerId);
    }
}