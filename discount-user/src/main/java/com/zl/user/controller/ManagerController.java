package com.zl.user.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.zl.common.base.ResultObject;
import com.zl.common.constants.NotifyTemplateEnum;
import com.zl.common.entity.IdEntity;
import com.zl.user.bo.ManagerBo;
import com.zl.user.dto.add.ManagerAddDTO;
import com.zl.user.dto.query.ManagerLoginQueryDTO;
import com.zl.user.dto.query.ManagerQueryDTO;
import com.zl.user.dto.update.ManagerUpdateDTO;
import com.zl.user.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zhangliang on 2019/6/6
 */
@Api(tags = "manager接口")
@Valid
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @ApiOperation("新增")
    @PostMapping(value="/add")
    @SuppressWarnings("all")
    public ResultObject add(@RequestBody ManagerAddDTO managerAddDTO, @Ignore ResultObject resultObject) {
        managerService.add(managerAddDTO);
        return resultObject;
    }

    @ApiOperation("删除")
    @DeleteMapping(value="/deleteById/{id}")
    @SuppressWarnings("all")
    public ResultObject deleteById(@NotNull(message = "id 不能为空") @PathVariable("id") Long id, @Ignore ResultObject resultObject) {
        managerService.deleteById(id);
        return resultObject;
    }


    @ApiOperation("管理员登录")
    @PostMapping(value="/login")
    @SuppressWarnings("all")
    public ResultObject login(@Valid @RequestBody ManagerLoginQueryDTO managerLoginQueryDTO, @Ignore ResultObject resultObject) {
        resultObject.setData(managerService.login(managerLoginQueryDTO));
        return resultObject;
    }

    @ApiOperation("获取管理员信息")
    @GetMapping(value="/info")
    @SuppressWarnings("all")
    public ResultObject info(@Ignore ResultObject resultObject) {
        resultObject.setData(managerService.getInfo());
        return resultObject;
    }

    @ApiOperation("分页查询管理员")
    @PostMapping(value="/getByParam")
    @SuppressWarnings("all")
    public ResultObject getByParam(@Valid @RequestBody ManagerQueryDTO managerQueryDTO, @Ignore ResultObject resultObject) {
        resultObject.setData(new PageInfo<ManagerBo>(managerService.getByParam(managerQueryDTO)));
        return resultObject;
    }

    @ApiOperation("更新")
    @PostMapping(value="/updateById")
    @SuppressWarnings("all")
    public ResultObject updateById(@Valid @RequestBody ManagerUpdateDTO managerUpdateDTO, @Ignore ResultObject resultObject) {
        managerService.updateById(managerUpdateDTO);
        return resultObject;
    }

    @ApiOperation("通知")
    @GetMapping(value="/notice")
    @SuppressWarnings("all")
    public void notice(@RequestParam("cityCode") String cityCode,@RequestParam("customerId")String customerId) {
        managerService.notice(cityCode,customerId);
    }

}
