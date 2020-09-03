package com.zl.user.bo;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

/**
* @author zhangliang
* @date 2019-11-18
*/
@Data
public class CustomerLittleBO {

    private String openId;

    private String nickName;

    private String avatarUrl;
}