package com.cool.admin.itemInfo;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsCateDto;
import com.cool.dto.AsInfoDto;

import java.util.*;

@Mapper
public interface ItemInfoMapper {
    public List<HashMap<String, Object>> itemInfoItemCate(AsCateDto body) throws Exception;

    public int itemInfoCnt(AsInfoDto formData) throws Exception;

    public List<HashMap<String, Object>> itemInfoList(AsInfoDto formData) throws Exception;

    public HashMap<String, Object> itemInfoNo(AsInfoDto body) throws Exception;

    public void itemInfoPost(AsInfoDto body) throws Exception;

    public void itemInfoPut(AsInfoDto body) throws Exception;

    public void itemInfoDelete(AsInfoDto body) throws Exception;

    public List<HashMap<String, Object>> itemInfoOL(AsInfoDto body) throws Exception;
}
