package com.awaion.demo009.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Role extends BaseDomain {
    private String sn;
    private String name;
    private List<Permission> permissions = new ArrayList<Permission>();
}
