package com.cool.admin.cus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsDto;
import com.cool.dto.CusDto;

import java.util.*;

@Service
public class CusService {
    @Autowired
    CusMapper mapper;

    public int cusCnt(CusDto formData) throws Exception {
        return mapper.cusCnt(formData);
    }

    public List<HashMap<String, Object>> cusList(CusDto formData) throws Exception {
        return mapper.cusList(formData);
    }

    public HashMap<String, Object> cusNo(CusDto formData) throws Exception {
        return mapper.cusNo(formData);
    }

    public int cusAsCnt(AsDto formData) throws Exception {
        return mapper.cusAsCnt(formData);
    }

    public List<HashMap<String, Object>> cusAsList(AsDto formData) throws Exception {
        return mapper.cusAsList(formData);
    }

    public void cusPost(CusDto formData) throws Exception {
        mapper.cusPost(formData);
    }

    public void cusPut(CusDto formData) throws Exception {
        mapper.cusPut(formData);
    }

    public void cusDelete(CusDto formData) throws Exception {
        mapper.cusDelete(formData);
    }

    public List<HashMap<String, Object>> cusOL(CusDto formData) throws Exception {
        return mapper.cusOL(formData);
    }

    public List<HashMap<String, Object>> cusDownload(CusDto formData) throws Exception {
        return mapper.cusDownload(formData);
    }

    public List<HashMap<String, Object>> cusName(CusDto formData) throws Exception {
        return mapper.cusName(formData);
    }
}
