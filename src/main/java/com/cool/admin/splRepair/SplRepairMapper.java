package com.cool.admin.splRepair;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.SplRepairDto;

import java.util.*;

@Mapper
public interface SplRepairMapper {
    public int splRepairCnt(SplRepairDto formData) throws Exception;

    public List<HashMap<String, Object>> splRepairList(SplRepairDto formData) throws Exception;

    public HashMap<String, Object> splRepairNo(SplRepairDto body) throws Exception;

    public void splRepairPost(SplRepairDto body) throws Exception;

    public void splRepairPut(SplRepairDto body) throws Exception;

    public void splRepairDelete(SplRepairDto body) throws Exception;

    public List<HashMap<String, Object>> splRepairOL(SplRepairDto body) throws Exception;

    public List<HashMap<String, Object>> splRepairDownload(SplRepairDto body) throws Exception;
}
