package com.zl.user.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

/**
 * author:zhangliang
 */
public class UserException extends BaseException {

    public UserException(ErrorCodeConstants constants) {
        super(constants);
    }
}
