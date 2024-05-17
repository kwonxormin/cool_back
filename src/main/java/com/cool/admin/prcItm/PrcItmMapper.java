package com.cool.admin.prcItm;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.PrcItmDto;

import java.util.*;

@Mapper
public interface PrcItmMapper {
    public int prcItmCnt(PrcItmDto formData) throws Exception;

    public List<HashMap<String, Object>> prcItmList(PrcItmDto formData) throws Exception;

    public HashMap<String, Object> prcItmNo(PrcItmDto body) throws Exception;

    public void prcItmPost(PrcItmDto body) throws Exception;

    public void prcItmPut(PrcItmDto body) throws Exception;

    public void prcItmDelete(PrcItmDto body) throws Exception;

    public List<HashMap<String, Object>> prcItmOL(PrcItmDto body) throws Exception;
}
