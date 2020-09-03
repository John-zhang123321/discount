package com.zl.user.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.user.bo.BaseRegionBo;
import com.zl.user.dto.query.BaseRegionQueryDTO;
import com.zl.user.entity.BaseRegion;
import com.zl.user.mapper.BaseRegionMapper;
import com.zl.user.service.BaseRegionService;
import com.zl.user.utils.BaseRegionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/5/24
 */
@Service
public class BaseRegionServiceImpl implements BaseRegionService {

    @Autowired
    BaseRegionMapper baseRegionMapper;


    @Override
    public void insert(List<BaseRegion> baseRegions) {
        for (BaseRegion baseRegion : baseRegions) {
            baseRegionMapper.insert(baseRegion);
        }
    }

    @Override
    public List<BaseRegionBo> getCityByParam(BaseRegionQueryDTO baseRegionQueryDTO) {
        List<BaseRegionBo> baseRegionBos = baseRegionMapper.getCityByParam(baseRegionQueryDTO);

        if (CollectionUtil.isEmpty(baseRegionBos)) {
            return baseRegionBos;
        }

        return BaseRegionUtil.getBaseRegion(baseRegionBos,false);

    }

    @Override
    public Object getBaseRegion() {
        List<BaseRegionBo> baseRegionBos = baseRegionMapper.getBaseRegion();
        if (CollectionUtil.isEmpty(baseRegionBos)) {
            return baseRegionBos;
        }

        return BaseRegionUtil.getBaseRegion(baseRegionBos,true);
    }
}
