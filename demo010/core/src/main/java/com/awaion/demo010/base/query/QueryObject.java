package com.awaion.demo010.base.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {

    private Integer currentPage = 1;
    private Integer pageSize = 5;

    public Integer getStart() {
        return (this.currentPage - 1) * this.pageSize;
    }

}
