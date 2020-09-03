package com.zl.gateway.service.impl;

import com.zl.gateway.entity.Blacklist;
import com.zl.gateway.entity.ExcludePath;
import com.zl.gateway.mapper.BlacklistMapper;
import com.zl.gateway.mapper.ExcludePathMapper;
import com.zl.gateway.service.BlacklistService;
import com.zl.gateway.service.ExcludePathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangliang
 * @date 2020/8/1
 */
@Service
public class BlacklistServiceImpl implements BlacklistService {
    @Autowired
    private BlacklistMapper blacklistMapper;
    @Override
    public List<Blacklist> getBySystem(String system) {
        return blacklistMapper.getBySystem(system);
    }
}
