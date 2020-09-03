//package com.zl.user.controller;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.zl.common.base.ResultObject;
//import com.zl.common.util.RequestUtil;
//import com.zl.log.annotation.MyType;
//import com.zl.log.annotation.Type;
//import com.zl.user.entity.Report;
//import com.zl.user.service.ReportService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.annotations.ApiIgnore;
//
///**
// * Created by zhangliang on 2019/6/6
// */
//@Api(tags = "举报接口")
//@RestController
//@RequestMapping("/report")
//public class ReportController {
//
//    @Autowired
//    ReportService reportService;
//
//    @Autowired
//    RequestUtil requestUtil;
//
//    @ApiOperation("新增")
//    @Type(MyType.ADD)
//    @RequestMapping(value="/add")
//    @SuppressWarnings("all")
//    public ResultObject add(@RequestBody Report report, @ApiIgnore ResultObject resultObject) {
//        reportService.insertSelective(report);
//        return resultObject;
//    }
//
//    @ApiOperation("删除")
//    @Type(MyType.DELETE)
//    @PostMapping(value="/deleteById")
//    @SuppressWarnings("all")
//    public ResultObject deleteById(@RequestBody Report report, ResultObject resultObject) {
//        reportService.deleteByPrimaryKey(report);
//        return resultObject;
//    }
//
//    @ApiOperation("更新")
//    @Type(MyType.UPDATE)
//    @PostMapping(value="/updateById")
//    @SuppressWarnings("all")
//    public ResultObject updateById(@RequestBody Report report, ResultObject resultObject) {
//        reportService.updateByPrimaryKeySelective(report);
//        return resultObject;
//    }
//
//
//
//    @ApiOperation("分页查询")
//    @Type(MyType.QUERY)
//    @PostMapping(value="/getByParam")
//    @SuppressWarnings("all")
//    public ResultObject getByParam(@RequestBody Report report, ResultObject resultObject) {
//        String uid = requestUtil.getWxToken();
//        report.setUserId(uid);
//        PageHelper.startPage(report.getPage(),report.getSize());
//        resultObject.setData(new PageInfo<Report>(reportService.select(report)));
//        return resultObject;
//    }
//
//    @ApiOperation("管理员分页查询")
//    @Type(MyType.QUERY)
//    @PostMapping(value="/getAll")
//    @SuppressWarnings("all")
//    public ResultObject getAll(@RequestBody Report report, ResultObject resultObject) {
//        PageHelper.startPage(report.getPage(),report.getSize());
//        resultObject.setData(new PageInfo<Report>(reportService.select(report)));
//        return resultObject;
//    }
//}
