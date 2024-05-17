package com.cool.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Alias("MtrMngDto")
public class MtrMngDto extends CommonDto {
    private int cmpn_no;
    private String mtr_mng_no;
    private String prch_no;
    private String item_code;
    private String item_name;
    private String standard;
    private String srttn;
    private String qntty;
    private String unit;
    private String link;
    private String mnfct;
    private String brand;
    private String model_name;
    private String section;
    private String wty_start;
    private String wty_end;
    private String note;
    private String mngr;
    private String prcsn_date;
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
