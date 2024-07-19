package com.awaion.demo008.util;

import com.awaion.demo008.domain.SystemMenu;

import java.util.List;

public class MenuUtil {
    public static String USERMENU = "MENUS_IN_SESSION";

    public static void filterAllMenu(List<SystemMenu> allmenu, List<Long> userMenuIds) {
        SystemMenu menu = null;
        for (int i = (allmenu.size() - 1); i >= 0; i--) {
            menu = allmenu.get(i);
            //如果菜单的id不在用户菜单集合中,则删除这个menu
            if (!userMenuIds.contains(menu.getId())) {
                allmenu.remove(i);
            } else {
                if (menu.getChildren() != null && menu.getChildren().size() > 0) {
                    filterAllMenu(menu.getChildren(), userMenuIds);
                }
            }
        }
    }
}
