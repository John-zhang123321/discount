package com.zl.setup.service;

import com.zl.setup.dto.add.CategoryAddDTO;
import com.zl.setup.dto.query.CategoryQueryDTO;
import com.zl.setup.dto.update.CategoryUpdateDTO;
import com.zl.setup.entity.Category;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by zhangliang on 2019/5/24
 */
@CacheConfig(cacheNames = "category")
public interface CategoryService {

    @CacheEvict(allEntries = true)
    void add(CategoryAddDTO categoryAddDTO, MultipartFile file);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);

    @CacheEvict(allEntries = true)
    void updateById(CategoryUpdateDTO categoryUpdateDTO, MultipartFile file);

    List<Category> getByParam(CategoryQueryDTO categoryQueryDTO);

    @Cacheable
    List<Category> getAll();

    Map getTitleByCategoryIds(List<String> categoryIds);

    List<Map<String,String>> chooseCategory();

}
