package com.cool.auth;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.LoginDto;

@Mapper
public interface AuthMapper {
    // 관리자 로그인
    public LoginDto.adminResponse loginAdmin(LoginDto.request formData);

    // 고객 로그인
    public LoginDto.cusResponse loginCus(LoginDto.request formData);

    // 기사 로그인
    public LoginDto.repairResponse loginRepair(LoginDto.request formData);
}
