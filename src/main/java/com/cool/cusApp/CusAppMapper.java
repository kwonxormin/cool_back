package com.cool.cusApp;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsDto;

@Mapper
public interface CusAppMapper {
    public HashMap<String, Object> insItemNo(AsDto body) throws Exception;

    public List<HashMap<String, Object>> cusAppAsList(AsDto formData) throws Exception;

    public void asPutStatus(AsDto body) throws Exception;
}
