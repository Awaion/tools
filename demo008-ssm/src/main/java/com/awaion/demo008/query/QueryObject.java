package com.awaion.demo008.query;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryObject {
    private Integer page = 1;//当前页
    private Integer rows = 10;//每页条数

    public Integer getStart() {
        return (this.page - 1) * this.rows;
    }
}
