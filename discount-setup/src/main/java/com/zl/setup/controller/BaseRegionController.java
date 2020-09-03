package com.zl.setup.controller;

import com.zl.setup.entity.BaseRegion;
import com.zl.setup.service.BaseRegionService;
import com.zl.common.base.ResultObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

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
    public ResultObject deleteById(@RequestBody Map<String , List<BaseRegion>> baseRegionMap, @ApiIgnore ResultObject resultObject){
        baseRegionService.insert(baseRegionMap.get("baseRegion"));
        return resultObject;
    }
}
