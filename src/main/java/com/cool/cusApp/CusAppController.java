package com.cool.cusApp;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.admin.as.AsService;
import com.cool.admin.itemBom.ItemBomService;
import com.cool.dto.AsBomDto;
import com.cool.dto.AsDto;
import com.cool.dto.FileDto;
import com.cool.dto.InsItemDto;
import com.cool.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/cusApp")
@RestController
public class CusAppController {
    @Autowired
    AsService asService;

    @Autowired
    CusAppService service;

    @Autowired
    ItemBomService itemBomService;

    @GetMapping("/insItem")
    public HashMap<String, Object> insItemList(
            HttpSession session, InsItemDto formData) throws Exception {
        System.out.println("/cusApp/insItem");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("cus") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCus_no(((LoginDto.cusResponse) session.getAttribute("cus")).getCus_no());

        result.put("data", asService.asInsItemList(formData));

        return result;
    }

    @PostMapping("/as")
    public HashMap<String, Object> asPost(
            HttpSession session, AsDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/as/post");

        if (session.getAttribute("cus") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        result = service.insItemNo(formData);

        formData.setCus_no(((LoginDto.cusResponse) session.getAttribute("cus")).getCus_no());
        formData.setName(result.get("cus_name").toString());
        formData.setAddr1(result.get("addr1").toString());
        formData.setAddr2(result.get("addr2").toString());
        formData.setAddr3(result.get("addr3").toString());
        formData.setCmpn_no((int) result.get("cmpn_no"));
        formData.setPhone(result.get("phone").toString());

        asService.asPost(formData);

        if (formData.getImg_c().get(0).getSize() > 0) {
            FileDto fileDto = new FileDto();
            fileDto.setUpload_no(formData.getAs_no());

            fileDto.setUpload_table("tb_as");
            fileDto.setUpload_name("img_c");

            for (int i = 0; i < formData.getImg_c().size(); i++) {
                fileDto.setUpload_file(formData.getImg_c().get(i).getBytes());

                asService.filePost(fileDto);
            }
        }

        result.put("result", 200);

        return result;
    }

    @GetMapping("/as")
    public HashMap<String, Object> asList(
            HttpSession session, AsDto formData) throws Exception {
        System.out.println("/cusApp/as");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("cus") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCus_no(((LoginDto.cusResponse) session.getAttribute("cus")).getCus_no());

        result.put("data", service.cusAppAsList(formData));

        return result;
    }

    @GetMapping("/as/{id}")
    public HashMap<String, Object> asNo(@PathVariable String id, HttpSession session) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> asNo = new HashMap<String, Object>();
        System.out.println("/cusApp/as/{id}");

        if (session.getAttribute("cus") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        AsDto formData = new AsDto();
        formData.setAs_no(id);

        asNo = asService.asNo(formData);

        result.put("data", asNo);

        FileDto fileDto = new FileDto();
        fileDto.setUpload_no(asNo.get("as_no").toString());
        fileDto.setUpload_table("tb_as");

        fileDto.setUpload_name("img_s");
        result.put("img_s", asService.fileList(fileDto));

        fileDto.setUpload_name("img_e");
        result.put("img_e", asService.fileList(fileDto));

        fileDto.setUpload_name("img_c");
        result.put("img_c", asService.fileList(fileDto));

        formData.setCmpn_no(((LoginDto.cusResponse) session.getAttribute("cus")).getCmpn_no());
        result.put("cmpnList", asService.asCmpnList(formData));

        return result;
    }

    @PostMapping("/as/put")
    public HashMap<String, Object> asPut(
            HttpSession session, AsDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/as/put");

        if (session.getAttribute("cus") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        service.asPutStatus(formData);

        result.put("result", 200);

        return result;
    }

    @GetMapping("/cmpn")
    public HashMap<String, Object> cmpnList(
            HttpSession session) throws Exception {
        System.out.println("/cusApp/cmpn");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("cus") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        result.put("data", session.getAttribute("cus"));

        return result;
    }

    @GetMapping("/itemBom")
    public HashMap<String, Object> itemBomList(
            HttpSession session, AsBomDto formData) throws Exception {
        System.out.println("/cusApp/itemBom");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("cus") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.cusResponse) session.getAttribute("cus")).getCmpn_no());
        formData.setUsePageNavy(false);

        result.put("data", itemBomService.itemBomList(formData));

        return result;
    }
}
