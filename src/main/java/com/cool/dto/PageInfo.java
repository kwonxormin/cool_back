package com.cool.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageInfo {
    @JsonIgnore
    private boolean usePageNavy = true;
    @JsonIgnore
    private int pageSize = 2;              // page Size
    @JsonIgnore
    private String keyword = "";            // 검색 키워드
    @JsonIgnore
    private String searchField = "";        // 검색 조건
    @JsonIgnore
    private List<String> searchType;        // DB 검색 컬럼명 List

    private int pageIndex = 1;              // current page No
    private int recordSize = 10;            // 1page Data cnt
    private int totalCnt = 0;

    public int getOffset() {
        return (pageIndex - 1) * recordSize;
    }

    // 첫페이지 Index
    public int getFirstPageIndex() {
        return ((int) Math.floor((double) (pageIndex - 1) / pageSize) * pageSize) + 1;
    }

    // 현재페이지의 최종페이지
    public int getEndPageIndex() {
        if (getFirstPageIndex() + pageSize > getEndedPageIndex()) return getEndedPageIndex();
        else return getFirstPageIndex() - 1 + pageSize;
    }

    // 최종페이지
    public int getEndedPageIndex() {
        return (int) Math.ceil((double) totalCnt / recordSize);
    }
}
