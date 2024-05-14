package com.awaion.demo008.domain;

import genertor.ObjectProp;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ObjectProp("角色")
public class Role {
    private Long id;

    @ObjectProp("编号")
    private String sn;

    @ObjectProp("名称")
    private String name;

    @ObjectProp("权限集合")
    private List<Permission> permissions = new ArrayList<Permission>();

    private List<SystemMenu> menus = new ArrayList<SystemMenu>();
}
