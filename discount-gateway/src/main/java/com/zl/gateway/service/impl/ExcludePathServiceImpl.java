package com.zl.gateway.service.impl;

import com.zl.gateway.entity.ExcludePath;
import com.zl.gateway.mapper.ExcludePathMapper;
import com.zl.gateway.service.ExcludePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangliang
 * @date 2020/8/1
 */
@Service
public class ExcludePathServiceImpl implements ExcludePathService {
    @Autowired
    private ExcludePathMapper excludePathMapper;
    @Override
    public List<ExcludePath> getBySystem(String system) {
        return excludePathMapper.getBySystem(system);
    }
}
