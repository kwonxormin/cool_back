package com.cool.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Alias("SplRepairDto")
public class SplRepairDto extends CommonDto {
    private int cmpn_no;
    private String repair_no;
    private String spl_no;
    private String is_spl;
    private String id;
    private String pw = "";
    private String name;
    private String note;
    private String glasses_no = "";
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
