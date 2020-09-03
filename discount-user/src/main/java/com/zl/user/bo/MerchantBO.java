package com.zl.user.bo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-15
*/
@Data
public class MerchantBO {

    private Long id;

    private Long createUser;

    private Date createTime;

}