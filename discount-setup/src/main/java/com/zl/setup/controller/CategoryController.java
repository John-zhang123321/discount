package com.zl.setup.controller;

import com.github.pagehelper.PageInfo;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.FileException;
import com.zl.setup.dto.add.CategoryAddDTO;
import com.zl.setup.dto.query.CategoryQueryDTO;
import com.zl.setup.dto.update.CategoryUpdateDTO;
import com.zl.setup.entity.Category;
import com.zl.setup.service.CategoryService;
import com.zl.common.base.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/6/6
 */
@Api(tags = "分类接口")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService CategoryService;

    @ApiOperation("新增")
    @PutMapping(value="/add")
    @SuppressWarnings("all")
    public ResultObject add(CategoryAddDTO categoryAddDTO, @RequestParam(value = "file",required = true) MultipartFile file , @ApiIgnore ResultObject resultObject) {
        CategoryService.add(categoryAddDTO,file);
        return resultObject;
    }


    @ApiOperation("删除")
    @DeleteMapping(value="/deleteById/{id}")
    @SuppressWarnings("all")
    public ResultObject deleteById(@PathVariable("id") Long id, @ApiIgnore ResultObject resultObject) {
        CategoryService.deleteById(id);
        return resultObject;
    }


    @ApiOperation("修改")
    @PostMapping(value="/updateById")
    @SuppressWarnings("all")
    public ResultObject updateById(CategoryUpdateDTO categoryUpdateDTO,@RequestParam(value = "file",required = false) MultipartFile file , @ApiIgnore ResultObject resultObject) {
        CategoryService.updateById(categoryUpdateDTO,file);
        return resultObject;
    }


    @ApiOperation("分页查询")
    @PostMapping(value="/getByParam")
    @SuppressWarnings("all")
    public ResultObject getByParam(@RequestBody CategoryQueryDTO categoryQueryDTO, @ApiIgnore ResultObject resultObject) {
        resultObject.setData(new PageInfo<Category>(CategoryService.getByParam(categoryQueryDTO)));
        return resultObject;
    }

    @ApiOperation("查询所有分类")
    @GetMapping(value="/getAll")
    @SuppressWarnings("all")
    public ResultObject getAll(@ApiIgnore ResultObject resultObject) {
        resultObject.setData(CategoryService.getAll());
        return resultObject;
    }

    @ApiOperation("选择分类")
    @GetMapping(value="/chooseCategory")
    @SuppressWarnings("all")
    public ResultObject chooseCategory(@ApiIgnore ResultObject resultObject) {
        resultObject.setData(CategoryService.chooseCategory());
        return resultObject;
    }



    @ApiOperation("根据id获取title")
    @GetMapping(value="/getTitleByCategoryIds")
    @SuppressWarnings("all")
    public Map getTitleByCategoryIds(@RequestParam("categoryIds") List<String> categoryIds) {
        return CategoryService.getTitleByCategoryIds(categoryIds);
    }

}
