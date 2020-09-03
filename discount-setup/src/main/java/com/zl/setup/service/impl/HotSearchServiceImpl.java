package com.zl.setup.service.impl;

import com.zl.setup.entity.HotSearch;
import com.zl.setup.service.HotSearchService;
import com.zl.setup.dto.add.HotSearchAddDTO;
import com.zl.setup.dto.query.HotSearchQueryDTO;
import com.zl.setup.dto.update.HotSearchUpdateDTO;
import com.zl.setup.mapper.HotSearchMapper;
import com.zl.setup.bo.HotSearchBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.zl.common.utils.BeanUtil;
import java.util.List;
import com.github.pagehelper.PageHelper;


/**
* @author zhangliang
* @date 2020-01-04
*/
@Service
public class HotSearchServiceImpl implements HotSearchService {

    @Autowired
    private HotSearchMapper hotSearchMapper;

    @Override
    public void add(HotSearchAddDTO hotSearchAddDTO) {
        HotSearch hotSearch = BeanUtil.copyProperties(hotSearchAddDTO, HotSearch.class,true);
        hotSearchMapper.insertSelective(hotSearch);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        hotSearchMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(HotSearchUpdateDTO hotSearchUpdateDTO) {
        HotSearch hotSearch = BeanUtil.copyProperties(hotSearchUpdateDTO, HotSearch.class,false);
        hotSearchMapper.updateByPrimaryKeySelective(hotSearch);
    }


    @Override
    public List<HotSearchBO> getByParam(HotSearchQueryDTO hotSearchQueryDTO){
        PageHelper.startPage(hotSearchQueryDTO.getPageNum(), hotSearchQueryDTO.getPageSize());
        return hotSearchMapper.getByParam(hotSearchQueryDTO);
    }

}