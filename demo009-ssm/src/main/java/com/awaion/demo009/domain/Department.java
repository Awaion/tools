package com.awaion.demo009.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Department extends BaseDomain {
    private String sn;
    private String name;
}
