package com.zl.setup.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

public class GoodsException extends BaseException {

    public GoodsException(ErrorCodeConstants constants) {
        super(constants);
    }
}
