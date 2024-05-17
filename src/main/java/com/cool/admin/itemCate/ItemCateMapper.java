package com.cool.admin.itemCate;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsCateDto;

import java.util.*;

@Mapper
public interface ItemCateMapper {
    public int itemCateCnt(AsCateDto formData) throws Exception;

    public List<HashMap<String, Object>> itemCateList(AsCateDto formData) throws Exception;

    public HashMap<String, Object> itemCateNo(AsCateDto body) throws Exception;

    public void itemCatePost(AsCateDto body) throws Exception;

    public void itemCatePut(AsCateDto body) throws Exception;

    public void itemCateDelete(AsCateDto body) throws Exception;

    public List<HashMap<String, Object>> itemCateOL(AsCateDto body) throws Exception;
}
