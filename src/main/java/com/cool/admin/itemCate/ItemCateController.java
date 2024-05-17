package com.cool.admin.itemCate;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.AsCateDto;
import com.cool.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/itemCate")
@RestController
public class ItemCateController {
    @Autowired
    ItemCateService service;

    @GetMapping
    public HashMap<String, Object> itemCateList(
            HttpSession session, AsCateDto formData) throws Exception {
        System.out.println("/admin/itemCate");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.itemCateCnt(formData));

        result.put("data", service.itemCateList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> itemCateNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemCate/{id}");

        AsCateDto formData = new AsCateDto();
        formData.setItem_cate_no(id);

        result.put("data", service.itemCateNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> itemCatePost(
            HttpSession session, AsCateDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemCate/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.itemCateOL(formData);

        if (overlap.size() == 0) {
            service.itemCatePost(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> itemCatePut(
            HttpSession session, AsCateDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemCate/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.itemCateOL(formData);

        if (overlap.size() == 0) {
            service.itemCatePut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> itemCateDelete(
            HttpSession session, AsCateDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/itemCate/delete");

        String item_cate_no = formData.getItem_cate_no();

        if (item_cate_no.indexOf(",") == -1) {
            service.itemCateDelete(formData);
        } else {
            for (int i = 0; i < item_cate_no.split(",").length; i++) {
                formData.setItem_cate_no(item_cate_no.split(",")[i]);
                service.itemCateDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }
}
