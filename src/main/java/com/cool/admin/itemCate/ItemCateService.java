package com.cool.admin.itemCate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsCateDto;

import java.util.*;

@Service
public class ItemCateService {
    @Autowired
    ItemCateMapper mapper;

    public int itemCateCnt(AsCateDto formData) throws Exception {
        return mapper.itemCateCnt(formData);
    }

    public List<HashMap<String, Object>> itemCateList(AsCateDto formData) throws Exception {
        return mapper.itemCateList(formData);
    }

    public HashMap<String, Object> itemCateNo(AsCateDto formData) throws Exception {
        return mapper.itemCateNo(formData);
    }

    public void itemCatePost(AsCateDto formData) throws Exception {
        mapper.itemCatePost(formData);
    }

    public void itemCatePut(AsCateDto formData) throws Exception {
        mapper.itemCatePut(formData);
    }

    public void itemCateDelete(AsCateDto formData) throws Exception {
        mapper.itemCateDelete(formData);
    }

    public List<HashMap<String, Object>> itemCateOL(AsCateDto formData) throws Exception {
        return mapper.itemCateOL(formData);
    }
}
