package com.zl.user.service;

import com.zl.user.dto.add.LoginDTO;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface AuthorizeService {

    int login(LoginDTO loginDTO);

    int getUserType();

}
