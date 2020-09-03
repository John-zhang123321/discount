package com.zl.setup.service.impl;

import com.github.pagehelper.PageHelper;
import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.utils.BeanUtil;
import com.zl.common.utils.QcloudCOSUtil;
import com.zl.setup.bo.CategoryQueryBO;
import com.zl.setup.dto.add.CategoryAddDTO;
import com.zl.setup.dto.query.CategoryQueryDTO;
import com.zl.setup.dto.update.CategoryUpdateDTO;
import com.zl.setup.entity.Category;
import com.zl.setup.expection.CategoryException;
import com.zl.setup.mapper.CategoryMapper;
import com.zl.setup.mapper.ShopMapper;
import com.zl.setup.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ShopMapper shopMapper;

    @Override
    public void add(CategoryAddDTO categoryAddDTO, MultipartFile file) {
        String url = QcloudCOSUtil.upload(file);
        Category category = BeanUtil.copyProperties(categoryAddDTO, Category.class,true);
        category.setUrl(url);
        categoryMapper.insertSelective(category);
    }


    @Override
    public void deleteById(Long id) {
        if (shopMapper.hasShop4Category(id)) {
            throw new CategoryException(ErrorCodeConstants.CHECK_DISCOUNTDETAIL_EXIST_ERROR);
        }

        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(CategoryUpdateDTO categoryUpdateDTO, MultipartFile file) {
        Category category = BeanUtil.copyProperties(categoryUpdateDTO, Category.class,false);

        if (file != null) {
            category.setUrl(QcloudCOSUtil.upload(file));
        }

        categoryMapper.updateByPrimaryKeySelective(category);

    }

    @Override
    public List<Category> getByParam(CategoryQueryDTO categoryQueryDTO) {
        Category category = BeanUtil.copyProperties(categoryQueryDTO, Category.class,true);
        PageHelper.startPage(categoryQueryDTO.getPageNum(),categoryQueryDTO.getPageSize());
        return  categoryMapper.getByParam(category);
    }

    @Override
    public List<Category> getAll() {
        return  categoryMapper.selectAll();
    }

    @Override
    public Map getTitleByCategoryIds(List<String> categoryIds) {
        List<CategoryQueryBO> mapList = categoryMapper.getTitleByCategoryIds(categoryIds);
        return  mapList.stream().collect(Collectors.toMap(CategoryQueryBO::getId, CategoryQueryBO::getTitle));
    }

    @Override
    public List<Map<String,String>> chooseCategory() {
        return  categoryMapper.chooseCategory();
    }
}
