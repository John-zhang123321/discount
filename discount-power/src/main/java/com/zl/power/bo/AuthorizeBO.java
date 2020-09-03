package com.zl.power.bo;

import lombok.Data;

import java.util.List;

/**
 * Created by zhangliang on 2019/9/24
 * 用户授权信息
 */
@Data
public class AuthorizeBO {
    private List<PermissionBO> menus;//菜单

    private List<PermissionBO> btns;//按钮
}
