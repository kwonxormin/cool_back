package com.cool.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Alias("InsItemDto")
public class InsItemDto extends CommonDto {
    private int cmpn_no;
    private String insItem_no;
    private String repair_no;
    private String cus_name;
    private String cus_no;
    private String cns_name;
    private String repair_name;
    private String cmpn_name;
    private String item_name;
    private String standard;
    private String addr;
    private String note;
    private String idate;
    private String eqpmnList;
    private String floorPlan;

    private String cate = "";
    private String ob = "";

    private MultipartHttpServletRequest multipartRequest;

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
