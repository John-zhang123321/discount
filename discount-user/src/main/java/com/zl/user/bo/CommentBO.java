package com.zl.user.bo;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
* @author zhangliang
* @date 2019-12-05
*/
@Data
public class CommentBO {

    private Long id;

    private Long createUser;

    private String content;

    private Integer rate;

    private Long pid;

    private Long userId;

    private String nickName;

    private String avatarUrl;

    private Date createTime;

    private List<CommentImgBO> commentImgBOS;

    private CommentBO child;

    private boolean canReply;

}