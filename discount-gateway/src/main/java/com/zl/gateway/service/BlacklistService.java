package com.zl.gateway.service;

import com.zl.gateway.entity.Blacklist;
import com.zl.gateway.entity.ExcludePath;

import java.util.List;

/**
 * @author zhangliang
 * @date 2020/8/1
 */
public interface BlacklistService {

    List<Blacklist> getBySystem(String system);
}
