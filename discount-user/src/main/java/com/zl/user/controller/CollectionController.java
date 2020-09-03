//package com.zl.user.controller;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.zl.common.base.ResultObject;
//import com.zl.common.util.RequestUtil;
//import com.zl.user.entity.Collection;
//import com.zl.user.service.CollectionService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import springfox.documentation.annotations.ApiIgnore;
//
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by zhangliang on 2019/6/6
// */
//@Api(tags = "收藏接口")
//@RestController
//@RequestMapping("/collection")
//public class CollectionController {
//
//    @Autowired
//    CollectionService CollectionService;
//
//    @Autowired
//    RequestUtil requestUtil;
//
//    @ApiOperation("新增")
//    @PostMapping(value="/add")
//    @SuppressWarnings("all")
//    public ResultObject add(@RequestBody Collection collection, @ApiIgnore ResultObject resultObject) {
////        String uid = requestUtil.getWxToken();
////        collection.setCreateTime(new Date());
////        collection.setCreateUser(uid);
////        collection.setUserId(uid);
////        CollectionService.insertSelective(collection);
//        return resultObject;
//    }
//
//    @ApiOperation("删除支持批量")
//    @PostMapping(value="/deleteById")
//    @SuppressWarnings("all")
//    public ResultObject deleteById(@RequestBody List<Collection> Collections, ResultObject resultObject) {
//        for (Collection collection : Collections) {
//            CollectionService.deleteByPrimaryKey(collection);
//        }
//        return resultObject;
//    }
//
//    @ApiOperation("更新")
//    @PostMapping(value="/updateById")
//    @SuppressWarnings("all")
//    public ResultObject updateById(@RequestBody Collection collection, ResultObject resultObject) {
////        CollectionService.updateByPrimaryKeySelective(collection);
//        return resultObject;
//    }
//
//
//
//    @ApiOperation("分页查询")
//    @PostMapping(value="/getByParam")
//    @SuppressWarnings("all")
//    public ResultObject getByParam(@RequestBody Collection collection, ResultObject resultObject) {
////        long uid = requestUtil.getWxToken();
////        collection.setUserId(uid);
////        PageHelper.startPage(collection.getPage(),collection.getSize());
////
////        resultObject.setData(new PageInfo<Collection>(CollectionService.select(collection)));
//        return resultObject;
//    }
//
//
//    @ApiOperation("管理员分页查询")
//    @PostMapping(value="/getAll")
//    @SuppressWarnings("all")
//    public ResultObject getAll(@RequestBody Collection collection, ResultObject resultObject) {
////        PageHelper.startPage(collection.getPage(),collection.getSize());
////        resultObject.setData(new PageInfo<Collection>(CollectionService.select(collection)));
//        return resultObject;
//    }
//}
