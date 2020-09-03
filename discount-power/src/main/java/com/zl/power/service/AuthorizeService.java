package com.zl.power.service;

import com.zl.power.bo.AuthorizeBO;

/**
 * Created by zhangliang on 2019/5/24
 */
public interface AuthorizeService {

    AuthorizeBO getAuthorizeInfo(long uid);
}
