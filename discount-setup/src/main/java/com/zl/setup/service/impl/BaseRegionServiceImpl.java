package com.zl.setup.service.impl;

import com.zl.setup.entity.BaseRegion;
import com.zl.setup.mapper.BaseRegionMapper;
import com.zl.setup.service.BaseRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
