//package com.zl.user.controller;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.zl.common.base.ResultObject;
//import com.zl.common.util.RequestUtil;
//import com.zl.log.annotation.MyType;
//import com.zl.log.annotation.Type;
//import com.zl.user.entity.Post;
//import com.zl.user.entity.ReportType;
//import com.zl.user.service.ReportTypeService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import springfox.documentation.annotations.ApiIgnore;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by zhangliang on 2019/6/6
// */
//@Api(tags = "举报类型接口")
//@RestController
//@RequestMapping("/reportType")
//public class ReportTypeController {
//
//    @Autowired
//    ReportTypeService reportTypeService;
//
//    @ApiOperation("新增")
//    @Type(MyType.ADD)
//    @RequestMapping(value="/add")
//    @SuppressWarnings("all")
//    public ResultObject add(@RequestBody ReportType reportType, @ApiIgnore ResultObject resultObject) {
//        reportTypeService.insertSelective(reportType);
//        return resultObject;
//    }
//
//    @ApiOperation("删除")
//    @Type(MyType.DELETE)
//    @PostMapping(value="/deleteById")
//    @SuppressWarnings("all")
//    public ResultObject deleteById(@RequestBody ReportType reportType, ResultObject resultObject) {
//        reportTypeService.deleteByPrimaryKey(reportType);
//        return resultObject;
//    }
//
//    @ApiOperation("更新")
//    @Type(MyType.UPDATE)
//    @PostMapping(value="/updateById")
//    @SuppressWarnings("all")
//    public ResultObject updateById(@RequestBody ReportType reportType, ResultObject resultObject) {
//        reportTypeService.updateByPrimaryKeySelective(reportType);
//        return resultObject;
//    }
//
//
//
//    @ApiOperation("分页查询")
//    @Type(MyType.QUERY)
//    @PostMapping(value="/getByParam")
//    @SuppressWarnings("all")
//    public ResultObject getByParam(@RequestBody ReportType reportType, ResultObject resultObject) {
//        PageHelper.startPage(reportType.getPage(),reportType.getSize());
//        resultObject.setData(new PageInfo<ReportType>(reportTypeService.select(reportType)));
//        return resultObject;
//    }
//
//}
