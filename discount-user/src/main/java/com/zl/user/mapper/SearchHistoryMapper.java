package com.zl.user.mapper;

import com.zl.user.dto.query.SearchHistoryQueryDTO;
import com.zl.user.entity.SearchHistory;
import com.zl.user.bo.SearchHistoryBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2020-01-04
*/
public interface SearchHistoryMapper extends BaseMapper<SearchHistory> {

    List<SearchHistoryBO> getByParam(@Param("searchHistoryQueryDTO") SearchHistoryQueryDTO searchHistoryQueryDTO);

    void deleteByUserId(@Param("userId")long userId);
}