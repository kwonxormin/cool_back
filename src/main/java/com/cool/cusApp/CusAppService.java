package com.cool.cusApp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsDto;

@Service
public class CusAppService {
    @Autowired
    CusAppMapper mapper;

    public HashMap<String, Object> insItemNo(AsDto formData) throws Exception {
        return mapper.insItemNo(formData);
    }

    public List<HashMap<String, Object>> cusAppAsList(AsDto formData) throws Exception {
        return mapper.cusAppAsList(formData);
    }

    public void asPutStatus(AsDto formData) throws Exception {
        mapper.asPutStatus(formData);
    }

}
