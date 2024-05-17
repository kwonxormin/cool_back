package com.cool.admin.mtrMng;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.MtrMngDto;

import java.util.*;

@Mapper
public interface MtrMngMapper {
    public int mtrMngCnt(MtrMngDto formData) throws Exception;

    public List<HashMap<String, Object>> mtrMngList(MtrMngDto formData) throws Exception;

    public HashMap<String, Object> mtrMngNo(MtrMngDto body) throws Exception;

    public void mtrMngPost(MtrMngDto body) throws Exception;

    public void mtrMngPut(MtrMngDto body) throws Exception;

    public void mtrMngDelete(MtrMngDto body) throws Exception;
}
