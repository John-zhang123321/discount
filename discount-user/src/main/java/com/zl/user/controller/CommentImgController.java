package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.service.CommentImgService;
import com.zl.user.dto.add.CommentImgAddDTO;
import com.zl.user.dto.query.CommentImgQueryDTO;
import com.zl.user.dto.update.CommentImgUpdateDTO;
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
@Api(tags = "commentImg接口")
@RestController
@RequestMapping("commentImg")
public class CommentImgController {

    @Autowired
    private CommentImgService commentImgService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody CommentImgAddDTO commentImgAddDTO, @Ignore ResultObject resultObject){
        commentImgService.add(commentImgAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        commentImgService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody CommentImgUpdateDTO commentImgUpdateDTO, @Ignore ResultObject resultObject){
        commentImgService.updateById(commentImgUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody CommentImgQueryDTO commentImgQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(commentImgService.getByParam(commentImgQueryDTO)));
        return resultObject;
    }

}