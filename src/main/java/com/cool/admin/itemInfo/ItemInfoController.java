package com.cool.admin.itemInfo;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.admin.itemCate.ItemCateService;
import com.cool.dto.AsCateDto;
import com.cool.dto.AsInfoDto;
import com.cool.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/itemInfo")
@RestController
public class ItemInfoController {
    @Autowired
    ItemInfoService service;

    @Autowired
    ItemCateService cateService;

    @GetMapping("/itemCate")
    public HashMap<String, Object> itemCateCmpnNo(HttpSession session, AsCateDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemInfo/itemCate");

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

        result.put("data", cateService.itemCateList(formData));

        return result;
    }

    @GetMapping
    public HashMap<String, Object> itemInfoList(
            HttpSession session, AsInfoDto formData) throws Exception {
        System.out.println("/admin/itemInfo");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        formData.setTotalCnt(service.itemInfoCnt(formData));

        result.put("data", service.itemInfoList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> itemInfoNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemInfo/{id}");

        AsInfoDto formData = new AsInfoDto();
        formData.setItem_info_no(id);

        result.put("data", service.itemInfoNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> itemInfoPost(
            HttpSession session, AsInfoDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemInfo/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.itemInfoOL(formData);

        if (overlap.size() == 0) {
            service.itemInfoPost(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> itemInfoPut(
            HttpSession session, AsInfoDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemInfo/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.itemInfoOL(formData);

        if (overlap.size() == 0) {
            service.itemInfoPut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> itemInfoDelete(
            HttpSession session, AsInfoDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/itemInfo/delete");

        String item_info_no = formData.getItem_info_no();

        if (item_info_no.indexOf(",") == -1) {
            service.itemInfoDelete(formData);
        } else {
            for (int i = 0; i < item_info_no.split(",").length; i++) {
                formData.setItem_info_no(item_info_no.split(",")[i]);
                service.itemInfoDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }
}
