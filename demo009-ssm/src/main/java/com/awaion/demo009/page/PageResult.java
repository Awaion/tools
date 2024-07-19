package com.awaion.demo009.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResult {
    private Integer totalCount;
    private List<?> data;
    private Integer currentPage;
    private Integer pageSize;
    private Integer prevPage;
    private Integer nextPage;
    private Integer totalPage;

    public PageResult(Integer totalCount, List<?> data, Integer currentPage, Integer pageSize) {
        super();
        this.totalCount = totalCount;
        this.data = data;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize + 1);
        this.prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
    }

}
