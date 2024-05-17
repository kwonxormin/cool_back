package com.cool.adminApp;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.admin.as.AsService;
import com.cool.dto.AsDto;
import com.cool.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/adminApp")
@RestController
public class AdminAppController {
    @Autowired
    AsService asService;

    @Autowired
    AdminAppService service;

    @GetMapping("/as")
    public HashMap<String, Object> asList(
            HttpSession session, AsDto formData) throws Exception {
        System.out.println("/adminApp/as");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("adminApp") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("adminApp")).getCmpn_no());

        result.put("data", service.asList(formData));

        return result;
    }
}
