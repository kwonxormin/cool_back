package com.cool.admin.spl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.LoginDto;
import com.cool.dto.SplDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/spl")
@RestController
public class SplController {
    @Autowired
    SplService service;

    @GetMapping
    public HashMap<String, Object> splList(
            HttpSession session, SplDto formData) throws Exception {
        System.out.println("/admin/spl");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.splCnt(formData));

        result.put("data", service.splList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> splNo(@PathVariable int id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/spl/{id}");

        SplDto formData = new SplDto();
        formData.setCmpn_no(id);

        result.put("data", service.splNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> splPost(
            HttpSession session, SplDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/spl/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.splOL(formData);

        if (overlap.size() == 0) {
            service.splPost(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> splPut(
            HttpSession session, SplDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/spl/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.splOL(formData);

        if (overlap.size() == 0) {
            service.splPut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> splDelete(
            HttpSession session, SplDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/spl/delete");

        String no = formData.getSpl_no();

        if (no.indexOf(",") == -1) {
            service.splDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setSpl_no(no.split(",")[i]);
                service.splDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }
}
