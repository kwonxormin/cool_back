package com.cool.admin.itemInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsCateDto;
import com.cool.dto.AsInfoDto;

import java.util.*;

@Service
public class ItemInfoService {
    @Autowired
    ItemInfoMapper mapper;

    public List<HashMap<String, Object>> itemInfoItemCate(AsCateDto formData) throws Exception {
        return mapper.itemInfoItemCate(formData);
    }

    public int itemInfoCnt(AsInfoDto formData) throws Exception {
        return mapper.itemInfoCnt(formData);
    }

    public List<HashMap<String, Object>> itemInfoList(AsInfoDto formData) throws Exception {
        return mapper.itemInfoList(formData);
    }

    public HashMap<String, Object> itemInfoNo(AsInfoDto formData) throws Exception {
        return mapper.itemInfoNo(formData);
    }

    public void itemInfoPost(AsInfoDto formData) throws Exception {
        mapper.itemInfoPost(formData);
    }

    public void itemInfoPut(AsInfoDto formData) throws Exception {
        mapper.itemInfoPut(formData);
    }

    public void itemInfoDelete(AsInfoDto formData) throws Exception {
        mapper.itemInfoDelete(formData);
    }

    public List<HashMap<String, Object>> itemInfoOL(AsInfoDto formData) throws Exception {
        return mapper.itemInfoOL(formData);
    }
}
