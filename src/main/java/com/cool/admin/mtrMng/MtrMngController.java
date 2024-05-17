package com.cool.admin.mtrMng;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.LoginDto;
import com.cool.dto.MtrMngDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/mtrMng")
@RestController
public class MtrMngController {
    @Autowired
    MtrMngService service;

    @GetMapping
    public HashMap<String, Object> mtrMngList(
            HttpSession session, MtrMngDto formData) throws Exception {
        System.out.println("/admin/mtrMng");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.mtrMngCnt(formData));

        result.put("data", service.mtrMngList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> mtrMngNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/mtrMng/{id}");

        MtrMngDto formData = new MtrMngDto();
        formData.setMtr_mng_no(id);

        result.put("data", service.mtrMngNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> mtrMngPost(
            HttpSession session, MtrMngDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/mtrMng/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        service.mtrMngPost(formData);
        result.put("result", 200);

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> mtrMngPut(
            HttpSession session, MtrMngDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/mtrMng/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        service.mtrMngPut(formData);
        result.put("result", 200);

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> mtrMngDelete(
            HttpSession session, MtrMngDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/mtrMng/delete");

        String no = formData.getMtr_mng_no();

        if (no.indexOf(",") == -1) {
            service.mtrMngDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setMtr_mng_no(no.split(",")[i]);
                service.mtrMngDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }
}
