package com.awaion.demo008.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@ObjectProp("系统菜单")
public class SystemMenu {
    private Long id;

    @ObjectProp("名称")
    private String text;

    @ObjectProp("图标")
    private String iconCls;

    @ObjectProp("地址")
    private String url;

    private SystemMenu parent;

    private List<SystemMenu> children = new ArrayList<SystemMenu>();

    public Map<String, Object> getAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("url", url);
        return attributes;
    }
}

