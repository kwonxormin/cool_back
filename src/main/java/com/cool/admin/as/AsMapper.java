package com.cool.admin.as;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsDto;
import com.cool.dto.FileDto;
import com.cool.dto.InsItemDto;

import java.util.*;

@Mapper
public interface AsMapper {
    public int asCnt(AsDto formData) throws Exception;

    public List<HashMap<String, Object>> asList(AsDto formData) throws Exception;

    public HashMap<String, Object> asNo(AsDto body) throws Exception;

    public List<HashMap<String, Object>> asCmpnList(AsDto formData) throws Exception;

    public List<HashMap<String, Object>> asInsItemList(InsItemDto formData) throws Exception;

    public void asPost(AsDto body) throws Exception;

    public void asPut(AsDto body) throws Exception;

    public void asDelete(AsDto body) throws Exception;

    public List<HashMap<String, Object>> fileList(FileDto formData) throws Exception;

    public void filePost(FileDto body) throws Exception;

    public void fileDelete(FileDto body) throws Exception;
}
