package com.cool.admin.user;

import org.apache.ibatis.annotations.Mapper;

import com.cool.dto.AsDto;
import com.cool.dto.AdminDto;

import java.util.*;

@Mapper
public interface UserMapper {
    public int userCnt(AdminDto formData) throws Exception;

    public List<HashMap<String, Object>> userList(AdminDto formData) throws Exception;

    public HashMap<String, Object> userNo(AdminDto body) throws Exception;

    public int userAsCnt(AsDto formData) throws Exception;

    public List<HashMap<String, Object>> userAsList(AsDto formData) throws Exception;

    public void userPost(AdminDto body) throws Exception;

    public void userPut(AdminDto body) throws Exception;

    public void userDelete(AdminDto body) throws Exception;

    public List<HashMap<String, Object>> userOL(AdminDto body) throws Exception;

    public List<HashMap<String, Object>> userDownload(AdminDto body) throws Exception;

    public List<HashMap<String, Object>> userName(AdminDto body) throws Exception;

    public void userAcesPost(AdminDto body) throws Exception;

    public List<HashMap<String, Object>> userAcesNo(AdminDto body) throws Exception;

    public void userAcesDelete(AdminDto body) throws Exception;
}
