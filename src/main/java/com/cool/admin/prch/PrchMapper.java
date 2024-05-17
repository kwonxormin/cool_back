package com.cool.admin.prch;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.PrchDto;

import java.util.*;

@Mapper
public interface PrchMapper {
    public int prchCnt(PrchDto formData) throws Exception;

    public List<HashMap<String, Object>> prchList(PrchDto formData) throws Exception;

    public HashMap<String, Object> prchNo(PrchDto body) throws Exception;

    public void prchPost(PrchDto body) throws Exception;

    public void prchPut(PrchDto body) throws Exception;

    public void prchDelete(PrchDto body) throws Exception;

    public List<HashMap<String, Object>> prchOL(PrchDto body) throws Exception;
}
