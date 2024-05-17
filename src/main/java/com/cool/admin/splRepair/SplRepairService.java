package com.cool.admin.splRepair;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.SplRepairDto;

import java.util.*;

@Service
public class SplRepairService {
    @Autowired
    SplRepairMapper mapper;

    public int splRepairCnt(SplRepairDto formData) throws Exception {
        return mapper.splRepairCnt(formData);
    }

    public List<HashMap<String, Object>> splRepairList(SplRepairDto formData) throws Exception {
        return mapper.splRepairList(formData);
    }

    public HashMap<String, Object> splRepairNo(SplRepairDto formData) throws Exception {
        return mapper.splRepairNo(formData);
    }

    public void splRepairPost(SplRepairDto formData) throws Exception {
        mapper.splRepairPost(formData);
    }

    public void splRepairPut(SplRepairDto formData) throws Exception {
        mapper.splRepairPut(formData);
    }

    public void splRepairDelete(SplRepairDto formData) throws Exception {
        mapper.splRepairDelete(formData);
    }

    public List<HashMap<String, Object>> splRepairOL(SplRepairDto formData) throws Exception {
        return mapper.splRepairOL(formData);
    }

    public List<HashMap<String, Object>> splRepairDownload(SplRepairDto formData) throws Exception {
        return mapper.splRepairDownload(formData);
    }

}
