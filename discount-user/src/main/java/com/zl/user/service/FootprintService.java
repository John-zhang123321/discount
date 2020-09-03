package com.zl.user.service;

import com.zl.common.entity.MyPageInfo;
import com.zl.user.dto.add.FootprintAddDTO;
import com.zl.user.dto.query.CommentQueryDTO;
import com.zl.user.dto.query.FootprintQueryDTO;
import com.zl.user.entity.Footprint;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface FootprintService  {
    void add(FootprintAddDTO footprintAddDTO);

    void delete4Expire();

    MyPageInfo getByParam(FootprintQueryDTO footprintQueryDTO);
}
