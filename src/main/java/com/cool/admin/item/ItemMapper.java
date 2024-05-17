package com.cool.admin.item;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsItemDto;

import java.util.*;

@Mapper
public interface ItemMapper {
    public int itemCnt(AsItemDto formData) throws Exception;

    public List<HashMap<String, Object>> itemList(AsItemDto formData) throws Exception;

    public HashMap<String, Object> itemNo(AsItemDto body) throws Exception;

    public void itemPost(AsItemDto body) throws Exception;

    public void itemPut(AsItemDto body) throws Exception;

    public void itemDelete(AsItemDto body) throws Exception;

    public List<HashMap<String, Object>> itemOL(AsItemDto body) throws Exception;
}
