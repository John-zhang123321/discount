package com.zl.user.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

/**
 * Created by zhangliang on 2019/5/24
 */
public class ImgException extends BaseException {
    public ImgException(ErrorCodeConstants constants) {
        super(constants);
    }
}
