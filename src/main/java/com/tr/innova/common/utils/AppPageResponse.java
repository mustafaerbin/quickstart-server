package com.tr.innova.common.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
public class AppPageResponse<T> extends AppResponse<T> {


    private PageInfo pageInfo;

    public AppPageResponse(T data, PageInfo pageInfo) {
        super(data);
        this.pageInfo = pageInfo;
    }

    public AppPageResponse(T data) {
        super(data);
    }

    @Getter
    @Setter
    public static class PageInfo {
        private int currentPage;
        private int totalPages;
        private long totalElements;

    }
}

