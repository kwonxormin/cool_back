package com.cool.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.KpiDto;

@Mapper
public interface KpiMapper {
    // 메인 홈 KPI 조회
    public KpiDto.getResponse loginAdmin(KpiDto.getRequest formData);

}
