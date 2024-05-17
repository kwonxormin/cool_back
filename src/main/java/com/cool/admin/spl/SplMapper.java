package com.cool.admin.spl;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.SplDto;

import java.util.*;

@Mapper
public interface SplMapper {
    public int splCnt(SplDto formData) throws Exception;

    public List<HashMap<String, Object>> splList(SplDto formData) throws Exception;

    public HashMap<String, Object> splNo(SplDto body) throws Exception;

    public void splPost(SplDto body) throws Exception;

    public void splPut(SplDto body) throws Exception;

    public void splDelete(SplDto body) throws Exception;

    public List<HashMap<String, Object>> splOL(SplDto body) throws Exception;
}
