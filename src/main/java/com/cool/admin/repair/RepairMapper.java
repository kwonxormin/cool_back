package com.cool.admin.repair;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.RepairDto;

import java.util.*;

@Mapper
public interface RepairMapper {
    public int repairCnt(RepairDto formData) throws Exception;

    public List<HashMap<String, Object>> repairList(RepairDto formData) throws Exception;

    public HashMap<String, Object> repairNo(RepairDto body) throws Exception;

    public void repairPost(RepairDto body) throws Exception;

    public void repairPut(RepairDto body) throws Exception;

    public void repairDelete(RepairDto body) throws Exception;

    public List<HashMap<String, Object>> repairOL(RepairDto body) throws Exception;

    public List<HashMap<String, Object>> repairDownload(RepairDto body) throws Exception;
}
