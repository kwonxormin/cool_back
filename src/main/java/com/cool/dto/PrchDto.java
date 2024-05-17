package com.cool.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Alias("PrchDto")
public class PrchDto extends CommonDto {
    private int cmpn_no;
    private String prch_no;
    private String name;
    private String owner;
    private String tel;
    private String addr1;
    private String addr2;
    private String addr3;
    private String bizno;
    private String note;
    private String cate = "";
    private String ob = "";

    public PageInfo getPageInfo() {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageIndex(this.getPageIndex());
        pageInfo.setRecordSize(this.getRecordSize());
        pageInfo.setPageSize(this.getPageSize());
        pageInfo.setKeyword(this.getKeyword());
        pageInfo.setTotalCnt(this.getTotalCnt());
        return pageInfo;
    }
}
