package com.cool.adminApp;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsDto;

@Service
public class AdminAppService {
    @Autowired
    AdminAppMapper mapper;

    public List<HashMap<String, Object>> asList(AsDto formData) throws Exception {
        return mapper.asList(formData);
    }
}
