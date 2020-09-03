package com.zl.user.service.impl;

import com.zl.common.utils.RequestResponseUtil;
import com.zl.user.entity.SearchHistory;
import com.zl.user.service.SearchHistoryService;
import com.zl.user.dto.add.SearchHistoryAddDTO;
import com.zl.user.dto.query.SearchHistoryQueryDTO;
import com.zl.user.dto.update.SearchHistoryUpdateDTO;
import com.zl.user.mapper.SearchHistoryMapper;
import com.zl.user.bo.SearchHistoryBO;
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
public class SearchHistoryServiceImpl implements SearchHistoryService {

    @Autowired
    private SearchHistoryMapper searchHistoryMapper;

    @Override
    public void add(SearchHistoryAddDTO searchHistoryAddDTO) {
        SearchHistory searchHistory = BeanUtil.copyProperties(searchHistoryAddDTO, SearchHistory.class,true);
        searchHistoryMapper.insertSelective(searchHistory);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        searchHistoryMapper.deleteByPrimaryKey(id);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(SearchHistoryUpdateDTO searchHistoryUpdateDTO) {
        SearchHistory searchHistory = BeanUtil.copyProperties(searchHistoryUpdateDTO, SearchHistory.class,false);
        searchHistoryMapper.updateByPrimaryKeySelective(searchHistory);
    }


    @Override
    public List<SearchHistoryBO> getByParam(SearchHistoryQueryDTO searchHistoryQueryDTO){
        PageHelper.startPage(searchHistoryQueryDTO.getPageNum(), searchHistoryQueryDTO.getPageSize());
        return searchHistoryMapper.getByParam(searchHistoryQueryDTO);
    }

    @Override
    public void deleteByUserId() {
        searchHistoryMapper.deleteByUserId(RequestResponseUtil.getUserId());
    }

}