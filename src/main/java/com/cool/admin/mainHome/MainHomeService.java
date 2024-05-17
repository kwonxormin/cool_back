package com.cool.admin.mainHome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MainHomeService {
    @Autowired
    MainHomeMapper mapper;

    public List<HashMap<String, Object>> kpiAs(HashMap<String, Object> body) throws Exception {
        return mapper.kpiAs(body);
    }

    public List<HashMap<String, Object>> kpiTime(HashMap<String, Object> body) throws Exception {
        return mapper.kpiTime(body);
    }

    public List<HashMap<String, Object>> kpiDate(HashMap<String, Object> body) throws Exception {
        return mapper.kpiDate(body);
    }

    public List<HashMap<String, Object>> kpiDyear(HashMap<String, Object> body) throws Exception {
        return mapper.kpiDyear(body);
    }
}
