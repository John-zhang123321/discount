package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.dto.query.BaseRegionQueryDTO;
import com.zl.user.entity.BaseRegion;
import com.zl.user.service.BaseRegionService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("baseRegion")
public class BaseRegionController {

    @Autowired
    BaseRegionService baseRegionService;


    @ApiOperation("插入")
    @PostMapping(value = "/add")
    public ResultObject insert(@RequestBody Map<String , List<BaseRegion>> baseRegionMap, @ApiIgnore ResultObject resultObject){
        baseRegionService.insert(baseRegionMap.get("baseRegion"));
        return resultObject;
    }

    @ApiOperation("获取城市数据")
    @PostMapping(value = "/getCityByParam")
    public ResultObject getCityByParam(@Valid @RequestBody BaseRegionQueryDTO baseRegionQueryDTO, @ApiIgnore ResultObject resultObject){
        resultObject.setData(baseRegionService.getCityByParam(baseRegionQueryDTO));
        return resultObject;
    }

    @ApiOperation("获取城市数据")
    @PostMapping(value = "/getBaseRegion")
    public ResultObject getArea(@ApiIgnore ResultObject resultObject){
        resultObject.setData(baseRegionService.getBaseRegion());
        return resultObject;
    }
}
