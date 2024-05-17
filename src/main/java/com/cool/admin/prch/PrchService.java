package com.cool.admin.prch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.PrchDto;

import java.util.*;

@Service
public class PrchService {
    @Autowired
    PrchMapper mapper;

    public int prchCnt(PrchDto formData) throws Exception {
        return mapper.prchCnt(formData);
    }

    public List<HashMap<String, Object>> prchList(PrchDto formData) throws Exception {
        return mapper.prchList(formData);
    }

    public HashMap<String, Object> prchNo(PrchDto formData) throws Exception {
        return mapper.prchNo(formData);
    }

    public void prchPost(PrchDto formData) throws Exception {
        mapper.prchPost(formData);
    }

    public void prchPut(PrchDto formData) throws Exception {
        mapper.prchPut(formData);
    }

    public void prchDelete(PrchDto formData) throws Exception {
        mapper.prchDelete(formData);
    }

    public List<HashMap<String, Object>> prchOL(PrchDto formData) throws Exception {
        return mapper.prchOL(formData);
    }
}
