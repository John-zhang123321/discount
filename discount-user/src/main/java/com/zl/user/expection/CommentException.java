package com.zl.user.expection;


import com.zl.common.constants.ErrorCodeConstants;
import com.zl.common.exception.BaseException;

/**
 * Created by zhangliang on 2019/5/24
 */
public class CommentException extends BaseException {
    public CommentException(ErrorCodeConstants constants) {
        super(constants);
    }
}
