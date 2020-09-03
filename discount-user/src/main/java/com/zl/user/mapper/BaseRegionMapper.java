package com.zl.user.mapper;


import com.zl.user.bo.BaseRegionBo;
import com.zl.user.dto.query.BaseRegionQueryDTO;
import com.zl.user.entity.BaseRegion;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface BaseRegionMapper extends BaseMapper<BaseRegion> {
    List<BaseRegionBo> getByCityCode(@Param("cityCodes") List<String> cityCodes);

    List<BaseRegionBo> getCityByParam(@Param("baseRegionQueryDTO")BaseRegionQueryDTO baseRegionQueryDTO);

    List<BaseRegionBo> getBaseRegion();

}