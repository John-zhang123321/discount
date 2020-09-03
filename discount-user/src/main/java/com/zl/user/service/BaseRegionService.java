package com.zl.user.service;


import com.zl.user.bo.BaseRegionBo;
import com.zl.user.dto.query.BaseRegionQueryDTO;
import com.zl.user.entity.BaseRegion;

import java.util.List;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface BaseRegionService {



    void insert(List<BaseRegion> baseRegions);

    List<BaseRegionBo> getCityByParam(BaseRegionQueryDTO baseRegionQueryDTO);

    Object getBaseRegion();

}
