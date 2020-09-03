package com.zl.setup.mapper;


import com.zl.setup.bo.CategoryQueryBO;
import com.zl.setup.entity.Category;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> getByParam(@Param("category") Category category);

    List<CategoryQueryBO> getTitleByCategoryIds(@Param("categoryIds")List<String> categoryIds);

    List<Map<String,String>> chooseCategory();
}