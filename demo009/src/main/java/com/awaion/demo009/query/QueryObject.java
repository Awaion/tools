package com.awaion.demo009.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryObject {
    private Integer currentPage = 1;
    private Integer pageSize = 10;

    public Integer getStart() {
        return (this.currentPage - 1) * pageSize;
    }
}
