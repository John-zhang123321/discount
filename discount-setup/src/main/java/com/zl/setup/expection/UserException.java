package com.zl.setup.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

public class UserException extends BaseException {

    public UserException(ErrorCodeConstants constants) {
        super(constants);
    }
}
