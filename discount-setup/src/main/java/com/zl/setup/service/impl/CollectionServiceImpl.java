package com.zl.setup.service.impl;

import com.zl.setup.entity.Collection;
import com.zl.setup.service.CollectionService;
import com.zl.setup.dto.add.CollectionAddDTO;
import com.zl.setup.dto.query.CollectionQueryDTO;
import com.zl.setup.dto.update.CollectionUpdateDTO;
import com.zl.setup.mapper.CollectionMapper;
import com.zl.setup.bo.CollectionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;
import java.util.List;
import com.github.pagehelper.PageHelper;


/**
* @author zhangliang
* @date 2019-11-20
*/
@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public void add(CollectionAddDTO collectionAddDTO) {
        Collection collection = BeanUtil.copyProperties(collectionAddDTO, Collection.class,true);
        collectionMapper.insertSelective(collection);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        collectionMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(CollectionUpdateDTO collectionUpdateDTO) {
        Collection collection = BeanUtil.copyProperties(collectionUpdateDTO, Collection.class,false);
        collectionMapper.updateByPrimaryKeySelective(collection);
    }


    @Override
    public List<CollectionBO> getByParam(CollectionQueryDTO collectionQueryDTO){
        PageHelper.startPage(collectionQueryDTO.getPageNum(), collectionQueryDTO.getPageSize());
        return collectionMapper.getByParam(collectionQueryDTO);
    }

}