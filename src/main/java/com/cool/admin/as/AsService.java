package com.cool.admin.as;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsDto;
import com.cool.dto.FileDto;
import com.cool.dto.InsItemDto;

import java.util.*;

@Service
public class AsService {
    @Autowired
    AsMapper mapper;

    public int asCnt(AsDto formData) throws Exception {
        return mapper.asCnt(formData);
    }

    public List<HashMap<String, Object>> asList(AsDto formData) throws Exception {
        return mapper.asList(formData);
    }

    public HashMap<String, Object> asNo(AsDto formData) throws Exception {
        return mapper.asNo(formData);
    }

    public List<HashMap<String, Object>> asCmpnList(AsDto formData) throws Exception {
        return mapper.asCmpnList(formData);
    }

    public List<HashMap<String, Object>> asInsItemList(InsItemDto formData) throws Exception {
        return mapper.asInsItemList(formData);
    }

    public void asPost(AsDto formData) throws Exception {
        mapper.asPost(formData);
    }

    public void asPut(AsDto formData) throws Exception {
        mapper.asPut(formData);
    }

    public void asDelete(AsDto formData) throws Exception {
        mapper.asDelete(formData);
    }

    public List<HashMap<String, Object>> fileList(FileDto formData) throws Exception {
        return mapper.fileList(formData);
    }

    public void filePost(FileDto formData) throws Exception {
        mapper.filePost(formData);
    }

    public void fileDelete(FileDto formData) throws Exception {
        mapper.fileDelete(formData);
    }
}
