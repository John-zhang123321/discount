package com.zl.setup.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

public class ShopException extends BaseException {

    public ShopException(ErrorCodeConstants constants) {
        super(constants);
    }
}
