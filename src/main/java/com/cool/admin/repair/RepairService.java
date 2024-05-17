package com.cool.admin.repair;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.RepairDto;

@Service
public class RepairService {
    @Autowired
    RepairMapper mapper;
    
    public int repairCnt(RepairDto formData) throws Exception {
        return mapper.repairCnt(formData);
    }

    public List<HashMap<String, Object>> repairList(RepairDto formData) throws Exception {
        return mapper.repairList(formData);
    }

    public HashMap<String, Object> repairNo(RepairDto formData) throws Exception {
        return mapper.repairNo(formData);
    }

    public void repairPost(RepairDto formData) throws Exception {
        mapper.repairPost(formData);
    }

    public void repairPut(RepairDto formData) throws Exception {
        mapper.repairPut(formData);
    }

    public void repairDelete(RepairDto formData) throws Exception {
        mapper.repairDelete(formData);
    }

    public List<HashMap<String, Object>> repairOL(RepairDto formData) throws Exception {
        return mapper.repairOL(formData);
    }

    public List<HashMap<String, Object>> repairDownload(RepairDto formData) throws Exception {
        return mapper.repairDownload(formData);
    }
}
