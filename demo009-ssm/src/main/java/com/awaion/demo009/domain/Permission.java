package com.awaion.demo009.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Permission extends BaseDomain {
    private String name;//权限的名称
    private String expression;//权限表达式,主要是定位到那个方法
}
