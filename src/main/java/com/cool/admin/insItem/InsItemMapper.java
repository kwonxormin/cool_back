package com.cool.admin.insItem;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.InsItemDto;

@Mapper
public interface InsItemMapper {
    public int insItemCnt(InsItemDto formData) throws Exception;

    public List<HashMap<String, Object>> insItemList(InsItemDto formData) throws Exception;

    public HashMap<String, Object> insItemNo(InsItemDto body) throws Exception;

    public List<HashMap<String, Object>> insItemCmpn(InsItemDto formData) throws Exception;

    public List<HashMap<String, Object>> insItemRepair(InsItemDto formData) throws Exception;

    public void insItemPost(InsItemDto body) throws Exception;

    public void insItemPut(InsItemDto body) throws Exception;

    public void insItemDelete(InsItemDto body) throws Exception;

    public List<HashMap<String, Object>> insItemDownload(InsItemDto body) throws Exception;

    public List<HashMap<String, Object>> insItemName(InsItemDto body) throws Exception;
}
