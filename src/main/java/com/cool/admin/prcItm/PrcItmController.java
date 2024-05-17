package com.cool.admin.prcItm;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.admin.prch.PrchService;
import com.cool.dto.LoginDto;
import com.cool.dto.PrcItmDto;
import com.cool.dto.PrchDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/prcItm")
@RestController
public class PrcItmController {
    @Autowired
    PrcItmService service;

    @Autowired
    PrchService prchService;

    @GetMapping("/prch")
    public HashMap<String, Object> prcItmPrch(HttpSession session, PrchDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/prcItm/prch");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setUsePageNavy(false);

        result.put("data", prchService.prchList(formData));

        return result;
    }

    @GetMapping
    public HashMap<String, Object> prcItmList(
            HttpSession session, PrcItmDto formData) throws Exception {
        System.out.println("/admin/prcItm");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.prcItmCnt(formData));

        result.put("data", service.prcItmList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> prcItmNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/prcItm/{id}");

        PrcItmDto formData = new PrcItmDto();
        formData.setPrcItm_no(id);

        result.put("data", service.prcItmNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> prcItmPost(
            HttpSession session, PrcItmDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/prcItm/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.prcItmOL(formData);

        if (overlap.size() == 0) {
            service.prcItmPost(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> prcItmPut(
            HttpSession session, PrcItmDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/prcItm/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.prcItmOL(formData);

        if (overlap.size() == 0) {
            service.prcItmPut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> prcItmDelete(
            HttpSession session, PrcItmDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/prcItm/delete");

        String no = formData.getPrcItm_no();

        if (no.indexOf(",") == -1) {
            service.prcItmDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setPrcItm_no(no.split(",")[i]);
                service.prcItmDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }
}
