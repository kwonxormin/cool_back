package com.cool.admin.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cool.dto.AsDto;
import com.cool.dto.AdminDto;

import java.util.*;

@Service
public class UserService {
    @Autowired
    UserMapper mapper;

    public int userCnt(AdminDto formData) throws Exception {
        return mapper.userCnt(formData);
    }

    public List<HashMap<String, Object>> userList(AdminDto formData) throws Exception {
        return mapper.userList(formData);
    }

    public HashMap<String, Object> userNo(AdminDto formData) throws Exception {
        return mapper.userNo(formData);
    }

    public int userAsCnt(AsDto formData) throws Exception {
        return mapper.userAsCnt(formData);
    }

    public List<HashMap<String, Object>> userAsList(AsDto formData) throws Exception {
        return mapper.userAsList(formData);
    }

    public void userPost(AdminDto formData) throws Exception {
        mapper.userPost(formData);
    }

    public void userPut(AdminDto formData) throws Exception {
        mapper.userPut(formData);
    }

    public void userDelete(AdminDto formData) throws Exception {
        mapper.userDelete(formData);
    }

    public List<HashMap<String, Object>> userOL(AdminDto formData) throws Exception {
        return mapper.userOL(formData);
    }

    public List<HashMap<String, Object>> userDownload(AdminDto formData) throws Exception {
        return mapper.userDownload(formData);
    }

    public List<HashMap<String, Object>> userName(AdminDto formData) throws Exception {
        return mapper.userName(formData);
    }

    public void userAcesPost(AdminDto formData) throws Exception {
        mapper.userAcesPost(formData);
    }

    public List<HashMap<String, Object>> userAcesNo(AdminDto formData) throws Exception {
        return mapper.userAcesNo(formData);
    }

    public void userAcesDelete(AdminDto formData) throws Exception {
        mapper.userAcesDelete(formData);
    }
}
