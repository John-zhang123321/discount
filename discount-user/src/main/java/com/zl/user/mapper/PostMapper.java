package com.zl.user.mapper;

import tk.mybatis.mapper.common.BaseMapper;
import com.zl.user.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PostMapper extends BaseMapper<Post> {
    List<Map> getNewPosts(@Param("post") Post post);

    List<Map> getPopularPosts(@Param("post")Post post);
}