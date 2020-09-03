package com.zl.user.bo;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-11-18
*/
@Data
public class CustomerBO {

    private Long id;

    private Long createUser;

    private Date createTime;

}