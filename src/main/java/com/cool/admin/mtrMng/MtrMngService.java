package com.cool.admin.mtrMng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.MtrMngDto;

import java.util.*;

@Service
public class MtrMngService {
    @Autowired
    MtrMngMapper mapper;

    public int mtrMngCnt(MtrMngDto formData) throws Exception {
        return mapper.mtrMngCnt(formData);
    }

    public List<HashMap<String, Object>> mtrMngList(MtrMngDto formData) throws Exception {
        return mapper.mtrMngList(formData);
    }

    public HashMap<String, Object> mtrMngNo(MtrMngDto formData) throws Exception {
        return mapper.mtrMngNo(formData);
    }

    public void mtrMngPost(MtrMngDto formData) throws Exception {
        mapper.mtrMngPost(formData);
    }

    public void mtrMngPut(MtrMngDto formData) throws Exception {
        mapper.mtrMngPut(formData);
    }

    public void mtrMngDelete(MtrMngDto formData) throws Exception {
        mapper.mtrMngDelete(formData);
    }
}
