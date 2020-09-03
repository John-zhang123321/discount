package com.zl.user.bo;

import lombok.Data;
import java.util.Date;
/**
* @author zhangliang
* @date 2019-12-01
*/
@Data
public class FeedbackBO {

    private Long id;

    private String content;

    private String contact;

    private Long createUser;

    private Date createTime;

}