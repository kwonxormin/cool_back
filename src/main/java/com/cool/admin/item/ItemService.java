package com.cool.admin.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsItemDto;

import java.util.*;

@Service
public class ItemService {
    @Autowired
    ItemMapper mapper;

    public int itemCnt(AsItemDto formData) throws Exception {
        return mapper.itemCnt(formData);
    }

    public List<HashMap<String, Object>> itemList(AsItemDto formData) throws Exception {
        return mapper.itemList(formData);
    }

    public HashMap<String, Object> itemNo(AsItemDto formData) throws Exception {
        return mapper.itemNo(formData);
    }

    public void itemPost(AsItemDto formData) throws Exception {
        mapper.itemPost(formData);
    }

    public void itemPut(AsItemDto formData) throws Exception {
        mapper.itemPut(formData);
    }

    public void itemDelete(AsItemDto formData) throws Exception {
        mapper.itemDelete(formData);
    }

    public List<HashMap<String, Object>> itemOL(AsItemDto formData) throws Exception {
        return mapper.itemOL(formData);
    }
}
