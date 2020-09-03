package com.zl.power.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

/**
 * Created by zhangliang on 2019/9/18
 */
public class RoleException extends BaseException {
    public RoleException(ErrorCodeConstants constants) {
        super(constants);
    }
}
