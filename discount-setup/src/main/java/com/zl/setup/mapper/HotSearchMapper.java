package com.zl.setup.mapper;

import com.zl.setup.dto.query.HotSearchQueryDTO;
import com.zl.setup.entity.HotSearch;
import com.zl.setup.bo.HotSearchBO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;
import java.util.List;
/**
* @author zhangliang
* @date 2020-01-04
*/
public interface HotSearchMapper extends BaseMapper<HotSearch> {

    List<HotSearchBO> getByParam(@Param("hotSearchQueryDTO") HotSearchQueryDTO hotSearchQueryDTO);
}