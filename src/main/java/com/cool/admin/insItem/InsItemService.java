package com.cool.admin.insItem;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.InsItemDto;

@Service
public class InsItemService {
    @Autowired
    InsItemMapper mapper;

    public int insItemCnt(InsItemDto formData) throws Exception {
        return mapper.insItemCnt(formData);
    }

    public List<HashMap<String, Object>> insItemList(InsItemDto formData) throws Exception {
        return mapper.insItemList(formData);
    }

    public HashMap<String, Object> insItemNo(InsItemDto formData) throws Exception {
        return mapper.insItemNo(formData);
    }

    public List<HashMap<String, Object>> insItemCmpn(InsItemDto formData) throws Exception {
        return mapper.insItemCmpn(formData);
    }

    public List<HashMap<String, Object>> insItemRepair(InsItemDto formData) throws Exception {
        return mapper.insItemRepair(formData);
    }

    public void insItemPost(InsItemDto formData) throws Exception {
        mapper.insItemPost(formData);
    }

    public void insItemPut(InsItemDto formData) throws Exception {
        mapper.insItemPut(formData);
    }

    public void insItemDelete(InsItemDto formData) throws Exception {
        mapper.insItemDelete(formData);
    }

    public List<HashMap<String, Object>> insItemDownload(InsItemDto formData) throws Exception {
        return mapper.insItemDownload(formData);
    }

    public List<HashMap<String, Object>> insItemName(InsItemDto formData) throws Exception {
        return mapper.insItemName(formData);
    }
}
