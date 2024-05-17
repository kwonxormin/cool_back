package com.cool.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Alias("AsDto")
public class AsDto extends CommonDto {
    private int cmpn_no;
    private String as_no;
    private String cus_no;
    private String repair_no = "1";
    private String insItem_no;
    private String status = "1";
    private String name = "";
    private String phone = "";
    private String bdate = "";
    private String btime = "";
    private String addr1 = "";
    private String addr2 = "";
    private String addr3 = "";
    private String product = "";
    private String symptom = "";
    private String symptom_cus = "";
    private String note = "";
    private String edate;
    private String date_s;
    private String date_e;

    private String r_name;
    private String r_tel;

    private String cate = "";
    private String ob = "";

    private String week;
    
    private List<MultipartFile> img_s;
    private List<MultipartFile> img_e;
    private List<MultipartFile> img_c;

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
