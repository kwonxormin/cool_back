package com.cool.admin.prcItm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.PrcItmDto;

import java.util.*;

@Service
public class PrcItmService {
    @Autowired
    PrcItmMapper mapper;

    public int prcItmCnt(PrcItmDto formData) throws Exception {
        return mapper.prcItmCnt(formData);
    }

    public List<HashMap<String, Object>> prcItmList(PrcItmDto formData) throws Exception {
        return mapper.prcItmList(formData);
    }

    public HashMap<String, Object> prcItmNo(PrcItmDto formData) throws Exception {
        return mapper.prcItmNo(formData);
    }

    public void prcItmPost(PrcItmDto formData) throws Exception {
        mapper.prcItmPost(formData);
    }

    public void prcItmPut(PrcItmDto formData) throws Exception {
        mapper.prcItmPut(formData);
    }

    public void prcItmDelete(PrcItmDto formData) throws Exception {
        mapper.prcItmDelete(formData);
    }

    public List<HashMap<String, Object>> prcItmOL(PrcItmDto formData) throws Exception {
        return mapper.prcItmOL(formData);
    }
}
