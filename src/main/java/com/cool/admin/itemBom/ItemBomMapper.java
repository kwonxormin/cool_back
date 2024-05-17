package com.cool.admin.itemBom;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsBomDto;
import com.cool.dto.AsInfoDto;

@Mapper
public interface ItemBomMapper {
    public List<HashMap<String, Object>> itemBomItemInfo(AsInfoDto body) throws Exception;

    public int itemBomCnt(AsBomDto formData) throws Exception;

    public List<HashMap<String, Object>> itemBomList(AsBomDto formData) throws Exception;

    public HashMap<String, Object> itemBomNo(AsBomDto body) throws Exception;

    public void itemBomPost(AsBomDto body) throws Exception;

    public void itemBomPut(AsBomDto body) throws Exception;

    public void itemBomDelete(AsBomDto body) throws Exception;

    public List<HashMap<String, Object>> itemBomOL(AsBomDto body) throws Exception;
}
