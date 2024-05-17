package com.cool.admin.spl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.SplDto;

import java.util.*;

@Service
public class SplService {
    @Autowired
    SplMapper mapper;

    public int splCnt(SplDto formData) throws Exception {
        return mapper.splCnt(formData);
    }

    public List<HashMap<String, Object>> splList(SplDto formData) throws Exception {
        return mapper.splList(formData);
    }

    public HashMap<String, Object> splNo(SplDto formData) throws Exception {
        return mapper.splNo(formData);
    }

    public void splPost(SplDto formData) throws Exception {
        mapper.splPost(formData);
    }

    public void splPut(SplDto formData) throws Exception {
        mapper.splPut(formData);
    }

    public void splDelete(SplDto formData) throws Exception {
        mapper.splDelete(formData);
    }

    public List<HashMap<String, Object>> splOL(SplDto formData) throws Exception {
        return mapper.splOL(formData);
    }
}
