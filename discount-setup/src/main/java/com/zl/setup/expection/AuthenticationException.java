package com.zl.setup.expection;

import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

/**
 * Created by zhangliang on 2019/9/18
 */
public class AuthenticationException extends BaseException {
    public AuthenticationException(ErrorCodeConstants constants) {
        super(constants);
    }
}
