package com.zl.power.controller;

import com.zl.power.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    JobService jobService;



//    @ApiOperation("获取所有岗位")
//    @PostMapping(value = "/getByParam")
//    public ResultObject getByParam(@RequestBody JobQueryDTO jobQueryDTO, @ApiIgnore ResultObject resultObject){
//        PageHelper.startPage(jobQueryDTO.getPage(),jobQueryDTO.getSize());
//        resultObject.setData(new PageInfo<>(jobService.getByParam(jobQueryDTO)));
//        return resultObject;
//    }

//    @ApiOperation("根据部门id获取岗位")
//    @PostMapping(value = "/getJobsByDeptId")
//    public ResultObject getJobsByDeptId(@RequestBody Job job, @ApiIgnore ResultObject resultObject){
//        resultObject.setData(jobService.select(job));
//        return resultObject;
//    }
//
//    @ApiOperation("新增岗位")
//    @PostMapping(value = "/add")
//    public ResultObject add(@RequestBody Job job, @ApiIgnore ResultObject resultObject){
//        job.setId(snowflakeIdWorker.getNextId(IdPrefixSuffixEnum.JOB));
//        job.setCreateTime(new Date());
//        job.setCreateUser(requestUtil.getTokenObject().getUserId());
//        jobService.insertSelective(job);
//        return resultObject;
//    }
//
//    @ApiOperation("删除岗位")
//    @PostMapping(value = "/deleteById")
//    public ResultObject deleteById(@RequestBody Job job, @ApiIgnore ResultObject resultObject){
//        jobService.deleteByPrimaryKey(job);
//        return resultObject;
//    }
//
//    @ApiOperation("更新岗位")
//    @PostMapping(value = "/updateById")
//    public ResultObject updateById(@RequestBody Job job, @ApiIgnore ResultObject resultObject){
//        jobService.updateByPrimaryKeySelective(job);
//        return resultObject;
//    }
}
