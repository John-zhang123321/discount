package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.dto.add.CommentReplyDTO;
import com.zl.user.service.CommentService;
import com.zl.user.dto.add.CommentAddDTO;
import com.zl.user.dto.query.CommentQueryDTO;
import com.zl.user.dto.update.CommentUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
/**
* @author zhangliang
* @date 2019-12-05
*/
@Api(tags = "comment接口")
@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody CommentAddDTO commentAddDTO, @Ignore ResultObject resultObject){
        commentService.add(commentAddDTO);
        return resultObject;
    }
    @ApiOperation(value = "商家回复")
    @PutMapping(value = "/reply")
    public ResultObject reply(@Valid @RequestBody CommentReplyDTO commentReplyDTO, @Ignore ResultObject resultObject){
        commentService.reply(commentReplyDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        commentService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody CommentUpdateDTO commentUpdateDTO, @Ignore ResultObject resultObject){
        commentService.updateById(commentUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody CommentQueryDTO commentQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(commentService.getByParam(commentQueryDTO)));
        return resultObject;
    }

    @ApiOperation(value = "根据店铺id查询")
    @PostMapping(value = "/getByShopId")
    public ResultObject getByShopId(@Valid @RequestBody CommentQueryDTO commentQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(commentService.getByShopId(commentQueryDTO));
        return resultObject;
    }

    @ApiOperation(value = "根据店铺id获取评分")
    @GetMapping(value = "/getRateByShopId/{shopId}")
    public ResultObject getRateByShopId(@Valid @PathVariable("shopId") Long shopId,@Ignore ResultObject resultObject){
        resultObject.setData(commentService.getRateByShopId(shopId));
        return resultObject;
    }

}