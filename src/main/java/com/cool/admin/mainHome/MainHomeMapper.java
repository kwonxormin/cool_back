package com.cool.admin.mainHome;

import org.apache.ibatis.annotations.Mapper;

import java.util.*;

@Mapper
public interface MainHomeMapper {
    public List<HashMap<String, Object>> kpiAs(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> kpiTime(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> kpiDate(HashMap<String, Object> body) throws Exception;

    public List<HashMap<String, Object>> kpiDyear(HashMap<String, Object> body) throws Exception;
}
