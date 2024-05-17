package com.cool.admin.cus;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsDto;
import com.cool.dto.CusDto;

import java.util.*;

@Mapper
public interface CusMapper {
    public int cusCnt(CusDto formData) throws Exception;

    public List<HashMap<String, Object>> cusList(CusDto formData) throws Exception;

    public HashMap<String, Object> cusNo(CusDto body) throws Exception;

    public int cusAsCnt(AsDto formData) throws Exception;

    public List<HashMap<String, Object>> cusAsList(AsDto formData) throws Exception;

    public void cusPost(CusDto body) throws Exception;

    public void cusPut(CusDto body) throws Exception;

    public void cusDelete(CusDto body) throws Exception;

    public List<HashMap<String, Object>> cusOL(CusDto body) throws Exception;

    public List<HashMap<String, Object>> cusDownload(CusDto body) throws Exception;

    public List<HashMap<String, Object>> cusName(CusDto body) throws Exception;
}
