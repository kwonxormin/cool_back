package com.cool.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Alias("AdminDto")
public class AdminDto extends CommonDto {
    private int cmpn_no;
    private String admin_no;
    private String id;
    private String pw;
    private String name;
    private String phone;
    private String is_admin;
    private String note;
    private String fcm_token;

    private String aces_no;
    private String aces_no_list;
    private String aces_name;

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
