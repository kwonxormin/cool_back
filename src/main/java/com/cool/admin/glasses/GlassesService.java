package com.cool.admin.glasses;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.GlassesDto;

@Service
public class GlassesService {
    @Autowired
    GlassesMapper mapper;

    public int glassesCnt(GlassesDto formData) throws Exception {
        return mapper.glassesCnt(formData);
    }

    public List<HashMap<String, Object>> glassesList(GlassesDto formData) throws Exception {
        return mapper.glassesList(formData);
    }

    public HashMap<String, Object> glassesNo(GlassesDto formData) throws Exception {
        return mapper.glassesNo(formData);
    }

    public void glassesTokenPut(GlassesDto formData) throws Exception {
        mapper.glassesTokenPut(formData);
    }

    // public int glassesAsCnt(AsDto formData) throws Exception {
    //     return mapper.glassesAsCnt(formData);
    // }

    // public List<HashMap<String, Object>> glassesAsList(AsDto formData) throws Exception {
    //     return mapper.glassesAsList(formData);
    // }

    // public void glassesPost(GlassesDto formData) throws Exception {
    //     mapper.glassesPost(formData);
    // }

    // public void glassesPut(GlassesDto formData) throws Exception {
    //     mapper.glassesPut(formData);
    // }

    // public void glassesDelete(GlassesDto formData) throws Exception {
    //     mapper.glassesDelete(formData);
    // }

    // public List<HashMap<String, Object>> glassesOL(GlassesDto formData) throws Exception {
    //     return mapper.glassesOL(formData);
    // }

    // public List<HashMap<String, Object>> glassesDownload(GlassesDto formData) throws Exception {
    //     return mapper.glassesDownload(formData);
    // }

    // public List<HashMap<String, Object>> glassesName(GlassesDto formData) throws Exception {
    //     return mapper.glassesName(formData);
    // }
}
