package com.cool.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Alias("CusDto")
public class CusDto extends CommonDto {
    private int cmpn_no;
    private String cus_no;
    private String name;
    private String phone;
    private String addr1;
    private String addr2;
    private String addr3;
    private String note;
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
