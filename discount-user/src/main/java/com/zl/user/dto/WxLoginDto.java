package com.zl.user.dto;

import com.zl.user.entity.User;
import lombok.Data;

/**
 * Created by zhangliang on 2019/7/6
 */
@Data
public class WxLoginDto extends User {
    private String code;
}
