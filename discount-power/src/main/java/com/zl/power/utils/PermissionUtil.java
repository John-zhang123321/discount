package com.zl.power.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.zl.power.bo.AuthorizeBO;
import com.zl.power.bo.PermissionBO;
import com.zl.power.bo.PermissionLittleBO;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zhangliang on 2019/9/24
 */
public class PermissionUtil {

    public static AuthorizeBO getAuthorizeInfo(List<PermissionBO> permissionBOS) {
        Map<Integer, List<PermissionBO>> collect = permissionBOS.stream().sorted(Comparator.comparing(PermissionBO::getSort)).collect(Collectors.groupingBy(PermissionBO::getType));

        List<PermissionBO> catalogs = collect.get(0);//目录

        if (CollectionUtil.isEmpty(catalogs)) {
            return null;
        }
        AuthorizeBO authorizeBO = new AuthorizeBO();

        List<PermissionBO> btns = collect.get(2);//按钮
        authorizeBO.setBtns(btns);

        List<PermissionBO> menus = collect.get(1);//菜单
        if(CollectionUtil.isEmpty(menus)) {
            authorizeBO.setMenus(catalogs);
            return authorizeBO;
        }

        menus.stream().forEach(menu -> menu.setMeta(meta(menu.getAlias(),menu.getIcon())));

        //菜单map
        Map<Long ,List<PermissionBO>> menuMap = menus.stream().collect(Collectors.groupingBy(PermissionBO::getPid));

        catalogs.forEach(catalog -> {
            catalog.setMeta(meta(catalog.getAlias(),catalog.getIcon()));
            catalog.setChildren(menuMap.get(catalog.getId()));
        });
        authorizeBO.setMenus(catalogs);
        return authorizeBO;
    }


    public static List<PermissionBO> createPermissionTree(List<PermissionBO> permissionBOS) {
        Map<Integer, List<PermissionBO>> collect = permissionBOS.stream().collect(Collectors.groupingBy(PermissionBO::getType));

        List<PermissionBO> catalogs = collect.get(0);//目录
        if(CollectionUtil.isEmpty(catalogs)){
            return null;
        }

        List<PermissionBO> menus = collect.get(1);//菜单
        if(CollectionUtil.isEmpty(menus)){
            return catalogs;
        }

        List<PermissionBO> btns = collect.get(2);//按钮
        if(CollectionUtil.isNotEmpty(btns)){
            for (PermissionBO menu : menus) {
                List<PermissionBO> filterBtns = btns.stream().filter(btn -> btn.getPid().equals(menu.getId())).collect(Collectors.toList());
                menu.setChildren(filterBtns);
            }
        }

        for (PermissionBO catalog : catalogs) {
            catalog.setMeta(meta(catalog.getAlias(),catalog.getIcon()));
            List<PermissionBO> filterMenus = menus.stream().filter(menu -> menu.getPid().equals(catalog.getId())).collect(Collectors.toList());
            catalog.setChildren(filterMenus);
        }

        return catalogs;

    }


    public static List<PermissionLittleBO> createPermissionLittle(List<PermissionLittleBO> permissionLittleBOS) {
        Map<Integer, List<PermissionLittleBO>> collect = permissionLittleBOS.stream().collect(Collectors.groupingBy(PermissionLittleBO::getType));

        List<PermissionLittleBO> catalogs = collect.get(0);//目录
        if(CollectionUtil.isEmpty(catalogs)){
            return null;
        }
        List<PermissionLittleBO> menus = collect.get(1);//菜单
        if(CollectionUtil.isEmpty(menus)){
            return catalogs;
        }
        List<PermissionLittleBO> btns = collect.get(2);//按钮

        if(CollectionUtil.isNotEmpty(btns)){
            for (PermissionLittleBO menu : menus) {
                List<PermissionLittleBO> filterBtns = btns.stream().filter(btn -> btn.getPid().equals(menu.getId())).collect(Collectors.toList());
                menu.setChildren(filterBtns);
            }
        }

        for (PermissionLittleBO catalog : catalogs) {
            List<PermissionLittleBO> filterMenus = menus.stream().filter(menu -> menu.getPid().equals(catalog.getId())).collect(Collectors.toList());
            catalog.setChildren(filterMenus);
        }

        return catalogs;
    }

    private static Map meta(String name,String icon) {
        Map map = new HashMap();
        map.put("title", name);
        map.put("icon", icon);
        return map;
    }
}
