package com.awaion.demo008.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeQueryObject extends QueryObject {
    private String keyword;

    private Long currentUserId;
}
