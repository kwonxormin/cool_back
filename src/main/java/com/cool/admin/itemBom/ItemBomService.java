package com.cool.admin.itemBom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsInfoDto;
import com.cool.dto.AsBomDto;

import java.util.*;

@Service
public class ItemBomService {
    @Autowired
    ItemBomMapper mapper;

    public List<HashMap<String, Object>> itemBomItemInfo(AsInfoDto formData) throws Exception {
        return mapper.itemBomItemInfo(formData);
    }

    public int itemBomCnt(AsBomDto formData) throws Exception {
        return mapper.itemBomCnt(formData);
    }

    public List<HashMap<String, Object>> itemBomList(AsBomDto formData) throws Exception {
        return mapper.itemBomList(formData);
    }

    public HashMap<String, Object> itemBomNo(AsBomDto formData) throws Exception {
        return mapper.itemBomNo(formData);
    }

    public void itemBomPost(AsBomDto formData) throws Exception {
        mapper.itemBomPost(formData);
    }

    public void itemBomPut(AsBomDto formData) throws Exception {
        mapper.itemBomPut(formData);
    }

    public void itemBomDelete(AsBomDto formData) throws Exception {
        mapper.itemBomDelete(formData);
    }

    public List<HashMap<String, Object>> itemBomOL(AsBomDto formData) throws Exception {
        return mapper.itemBomOL(formData);
    }
}
