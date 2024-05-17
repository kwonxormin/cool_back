package com.cool.admin.glasses;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.GlassesDto;

@Mapper
public interface GlassesMapper {
    public int glassesCnt(GlassesDto formData) throws Exception;

    public List<HashMap<String, Object>> glassesList(GlassesDto formData) throws Exception;

    public HashMap<String, Object> glassesNo(GlassesDto body) throws Exception;

    public void glassesTokenPut(GlassesDto body) throws Exception;

    // public int glassesAsCnt(AsDto formData) throws Exception;

    // public List<HashMap<String, Object>> glassesAsList(AsDto formData) throws Exception;

    // public void glassesPost(GlassesDto body) throws Exception;

    // public void glassesPut(GlassesDto body) throws Exception;

    // public void glassesDelete(GlassesDto body) throws Exception;

    // public List<HashMap<String, Object>> glassesOL(GlassesDto body) throws Exception;

    // public List<HashMap<String, Object>> glassesDownload(GlassesDto body) throws Exception;

    // public List<HashMap<String, Object>> glassesName(GlassesDto body) throws Exception;
}
