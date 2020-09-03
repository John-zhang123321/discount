package com.zl.user.controller;

import com.zl.common.base.ResultObject;
import com.zl.user.service.FeedbackService;
import com.zl.user.dto.add.FeedbackAddDTO;
import com.zl.user.dto.query.FeedbackQueryDTO;
import com.zl.user.dto.update.FeedbackUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import com.github.pagehelper.PageInfo;
import javax.validation.Valid;
/**
* @author zhangliang
* @date 2019-12-01
*/
@Api(tags = "feedback接口")
@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation(value = "新增")
    @PutMapping(value = "/add")
    public ResultObject add(@Valid @RequestBody FeedbackAddDTO feedbackAddDTO, @Ignore ResultObject resultObject){
        feedbackService.add(feedbackAddDTO);
        return resultObject;
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = "/deleteById/{id}")
    public ResultObject deleteById(@Valid @PathVariable("id") Long id, @Ignore ResultObject resultObject){
        feedbackService.deleteById(id);
        return resultObject;
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateById")
    public ResultObject updateById(@Valid @RequestBody FeedbackUpdateDTO feedbackUpdateDTO, @Ignore ResultObject resultObject){
        feedbackService.updateById(feedbackUpdateDTO);
        return resultObject;
    }

    @ApiOperation(value = "分页查询")
    @PostMapping(value = "/getByParam")
    public ResultObject getByParam(@Valid @RequestBody FeedbackQueryDTO feedbackQueryDTO,@Ignore ResultObject resultObject){
        resultObject.setData(new PageInfo(feedbackService.getByParam(feedbackQueryDTO)));
        return resultObject;
    }

}