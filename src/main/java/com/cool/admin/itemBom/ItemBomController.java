package com.cool.admin.itemBom;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.admin.itemInfo.ItemInfoService;
import com.cool.dto.AsBomDto;
import com.cool.dto.AsInfoDto;
import com.cool.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/itemBom")
@RestController
public class ItemBomController {
    @Autowired
    ItemBomService service;

    @Autowired
    ItemInfoService infoService;

    @GetMapping("/itemInfo")
    public HashMap<String, Object> itemInfoCmpnNo(HttpSession session, AsInfoDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/itemBom/itemInfo");

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

        result.put("data", infoService.itemInfoList(formData));

        return result;
    }

    @GetMapping
    public HashMap<String, Object> itemBomList(
            HttpSession session, AsBomDto formData) throws Exception {
        System.out.println("/admin/itemBom");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        formData.setTotalCnt(service.itemBomCnt(formData));

        result.put("data", service.itemBomList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> itemBomNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemBom/{id}");

        AsBomDto formData = new AsBomDto();
        formData.setItem_bom_no(id);

        result.put("data", service.itemBomNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> itemBomPost(
            HttpSession session, AsBomDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemBom/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.itemBomOL(formData);

        if (overlap.size() == 0) {
            service.itemBomPost(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> itemBomPut(
            HttpSession session, AsBomDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/itemBom/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.itemBomOL(formData);

        if (overlap.size() == 0) {
            service.itemBomPut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> itemBomDelete(
            HttpSession session, AsBomDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/itemBom/delete");

        String item_cate_no = formData.getItem_bom_no();

        if (item_cate_no.indexOf(",") == -1) {
            service.itemBomDelete(formData);
        } else {
            for (int i = 0; i < item_cate_no.split(",").length; i++) {
                formData.setItem_bom_no(item_cate_no.split(",")[i]);
                service.itemBomDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }
}
