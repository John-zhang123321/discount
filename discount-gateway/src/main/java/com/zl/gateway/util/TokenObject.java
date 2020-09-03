package com.zl.gateway.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by zhangliang on 2019/11/23
 */
@Data
@AllArgsConstructor
public class TokenObject {

    private Long userId;

    private String phone;

}
