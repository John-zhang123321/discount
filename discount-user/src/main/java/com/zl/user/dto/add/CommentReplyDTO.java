package com.zl.user.dto.add;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* @author zhangliang
* @date 2019-12-05
*/
@Data
public class CommentReplyDTO {

    // 店铺id
    @NotNull(message = "店铺id不能为空")
    private Long shopId;

    // 内容
    @NotBlank(message = "回复内容不能为空")
    private String content;

    // 创建人
    private Long createUser;

    private Long pid;

}