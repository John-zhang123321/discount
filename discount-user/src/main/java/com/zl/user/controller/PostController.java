//package com.zl.user.controller;
//import com.zl.log.annotation.MyType;
//import com.zl.log.annotation.Type;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.zl.common.base.ResultObject;
//import com.zl.common.util.RequestUtil;
//import com.zl.user.entity.Post;
//import com.zl.user.service.PostService;
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
//@Api(tags = "帖子接口")
//@RestController
//@RequestMapping("/post")
//public class PostController {
//
//    @Autowired
//    PostService postService;
//
//    @Autowired
//    RequestUtil requestUtil;
//
//    @ApiOperation("新增")
//    @Type(MyType.ADD)
//    @RequestMapping(value="/add")
//    @SuppressWarnings("all")
//    public ResultObject add( Post post, @RequestParam(value = "file",required = false) MultipartFile file , @ApiIgnore ResultObject resultObject) {
//        postService.insert(post,file);
//        return resultObject;
//    }
//
//    @ApiOperation("删除支持批量")
//    @Type(MyType.DELETE)
//    @PostMapping(value="/deleteById")
//    @SuppressWarnings("all")
//    public ResultObject deleteById(@RequestBody List<Post> posts, ResultObject resultObject) {
//        for (Post post : posts) {
//            postService.deleteByPrimaryKey(post);
//        }
//        return resultObject;
//    }
//
//    @ApiOperation("更新")
//    @Type(MyType.UPDATE)
//    @PostMapping(value="/updateById")
//    @SuppressWarnings("all")
//    public ResultObject updateById(@RequestBody Post post, ResultObject resultObject) {
//        postService.updateByPrimaryKeySelective(post);
//        return resultObject;
//    }
//
//
//
//    @ApiOperation("分页查询")
//    @Type(MyType.QUERY)
//    @PostMapping(value="/getByParam")
//    @SuppressWarnings("all")
//    public ResultObject getByParam(@RequestBody Post post, ResultObject resultObject) {
//        String uid = requestUtil.getWxToken();
//        post.setUserId(uid);
//        PageHelper.startPage(post.getPage(),post.getSize());
//
//        resultObject.setData(new PageInfo<Post>(postService.select(post)));
//        return resultObject;
//    }
//
//
//    @ApiOperation("管理员分页查询")
//    @Type(MyType.QUERY)
//    @PostMapping(value="/getAll")
//    @SuppressWarnings("all")
//    public ResultObject getAll(@RequestBody Post post, ResultObject resultObject) {
//        PageHelper.startPage(post.getPage(),post.getSize());
//        resultObject.setData(new PageInfo<Post>(postService.select(post)));
//        return resultObject;
//    }
//
//    @ApiOperation("获取热门帖子")
//    @Type(MyType.QUERY)
//    @PostMapping(value="/getPopularPosts")
//    @SuppressWarnings("all")
//    public ResultObject getPopularPosts(@RequestBody Post post, ResultObject resultObject) {
//        PageHelper.startPage(post.getPage(),post.getSize());
//        resultObject.setData(new PageInfo<Map>(postService.getPopularPosts(post)));
//        return resultObject;
//    }
//
//    @ApiOperation("获取最新帖子")
//    @Type(MyType.QUERY)
//    @PostMapping(value="/getNewPosts")
//    @SuppressWarnings("all")
//    public ResultObject getNewPosts(@RequestBody Post post, ResultObject resultObject) {
//        PageHelper.startPage(post.getPage(),post.getSize());
//        resultObject.setData(new PageInfo<Map>(postService.getNewPosts(post)));
//        return resultObject;
//    }
//}
