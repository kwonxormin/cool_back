package com.cool.adminApp;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsDto;

@Mapper
public interface AdminAppMapper {
    public List<HashMap<String, Object>> asList(AsDto formData) throws Exception;
}
