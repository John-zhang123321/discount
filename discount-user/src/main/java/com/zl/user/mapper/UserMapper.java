package com.zl.user.mapper;

import com.zl.user.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    String login(@Param("user") User user);

    int getCountById(@Param("openid")String openid);

    boolean checkAccount(@Param("openid")String phone);
}