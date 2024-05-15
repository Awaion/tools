package com.awaion.demo010.base.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PageResult {

    private List<?> data;
    private Integer totalCount;

    private Integer currentPage = 1;
    private Integer pageSize = 5;

    private Integer prevPage;
    private Integer nextPage;

    private Integer totalPage;

    public PageResult(List<?> data, Integer totalCount, Integer currentPage,
                      Integer pageSize) {
        super();
        this.data = data;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        // ---------------------------

        prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize
                : totalCount / pageSize + 1;
        nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
    }

    public Integer getTotalPage() {
        return totalPage == 0 ? 1 : totalPage;
    }


}
