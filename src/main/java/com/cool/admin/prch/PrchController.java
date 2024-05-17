package com.cool.admin.prch;

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
import com.cool.dto.PrchDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/prch")
@RestController
public class PrchController {
    @Autowired
    PrchService service;

    @GetMapping
    public HashMap<String, Object> prchList(
            HttpSession session, PrchDto formData) throws Exception {
        System.out.println("/admin/prch");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.prchCnt(formData));

        result.put("data", service.prchList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> prchNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/prch/{id}");

        PrchDto formData = new PrchDto();
        formData.setPrch_no(id);

        result.put("data", service.prchNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> prchPost(
            HttpSession session, PrchDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/prch/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.prchOL(formData);

        if (overlap.size() == 0) {
            service.prchPost(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> prchPut(
            HttpSession session, PrchDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/prch/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.prchOL(formData);

        if (overlap.size() == 0) {
            service.prchPut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> prchDelete(
            HttpSession session, PrchDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/prch/delete");

        String no = formData.getPrch_no();

        if (no.indexOf(",") == -1) {
            service.prchDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setPrch_no(no.split(",")[i]);
                service.prchDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }
}
