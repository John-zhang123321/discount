package com.zl.setup.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

public class CategoryException extends BaseException {

    public CategoryException(ErrorCodeConstants constants) {
        super(constants);
    }
}
