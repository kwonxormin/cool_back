package com.cool.admin.insItem;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.InsItemDto;
import com.cool.dto.LoginDto;
import com.cool.utills.MyBatisTransactionManager;
import com.cool.utills.MyBatisTransactionManagerBatch;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/insItem")
@RestController
public class InsItemController {
    @Autowired
    InsItemService service;

    @Autowired
    ApplicationContext applicationContext;

    public MyBatisTransactionManager getTransactionManager() {
        return applicationContext.getBean(MyBatisTransactionManager.class);
    }

    public MyBatisTransactionManagerBatch getTransactionManagerBatch() {
        return applicationContext.getBean(MyBatisTransactionManagerBatch.class);
    }

    @GetMapping
    public HashMap<String, Object> insItemList(
            HttpSession session, InsItemDto formData) throws Exception {
        System.out.println("/admin/insItem");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.insItemCnt(formData));

        result.put("data", service.insItemList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> insItemNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/insItem/{id}");

        InsItemDto formData = new InsItemDto();
        formData.setInsItem_no(id);

        result.put("data", service.insItemNo(formData));

        return result;
    }

    @GetMapping("/cmpn")
    public HashMap<String, Object> insItemCmpn(
            HttpSession session, InsItemDto formData) throws Exception {
        System.out.println("/admin/insItem/cmpn");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        result.put("data", service.insItemCmpn(formData));

        return result;
    }

    @GetMapping("/repair")
    public HashMap<String, Object> insItemRepair(
            HttpSession session, InsItemDto formData) throws Exception {
        System.out.println("/admin/insItem/repair");

        HashMap<String, Object> result = new HashMap<String, Object>();

        result.put("data", service.insItemRepair(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> insItemPost(
            HttpSession session, InsItemDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/insItem/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        service.insItemPost(formData);
        result.put("result", 200);

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> insItemPut(
            HttpSession session, InsItemDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/insItem/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        service.insItemPut(formData);
        result.put("result", 200);

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> insItemDelete(
            HttpSession session, InsItemDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/insItem/delete");

        String no = formData.getInsItem_no();

        if (no.indexOf(",") == -1) {
            service.insItemDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setInsItem_no(no.split(",")[i]);
                service.insItemDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }

    // @PostMapping("/insItem/upload")
    // public HashMap<String, Object> insItemUpload(
    // HttpSession session, InsItemDto formData) throws Exception {
    // HashMap<String, Object> result = new HashMap<String, Object>();
    // List<HashMap<String, Object>> overlap = new ArrayList<HashMap<String,
    // Object>>();
    // MultipartHttpServletRequest multipartRequest =
    // formData.getMultipartRequest();
    // Iterator<String> itr = multipartRequest.getFileNames();
    // MyBatisTransactionManager transaction = getTransactionManager();
    // HashMap<String, Object> body2 = new HashMap<String, Object>();

    // try {
    // System.out.println("/admin/insItem/upload");

    // if (session.getAttribute("admin") == null) {
    // result.put("message", "로그인 후 이용 바랍니다.");
    // return result;
    // }

    // formData.setCmpn_no(((LoginDto.adminResponse)
    // session.getAttribute("admin")).getCmpn_no());

    // transaction.start();

    // while (itr.hasNext()) { // 파일을 하나씩 불러온다.
    // MultipartFile file = multipartRequest.getFile(itr.next());
    // String extension = FileNameUtils.getExtension(file.getOriginalFilename());

    // if (extension.equals("xlsx") || extension.equals("xls")) {
    // Workbook workbook = null;

    // if (extension.equals("xlsx")) {
    // workbook = new XSSFWorkbook(file.getInputStream());
    // } else if (extension.equals("xls")) {
    // workbook = new HSSFWorkbook(file.getInputStream());
    // }

    // Sheet worksheet = workbook.getSheetAt(0);

    // for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
    // Row row = worksheet.getRow(i);

    // if (row.getCell(0) == null) {
    // formData.setName("");
    // } else {
    // if (row.getCell(0).getCellType() == CellType.NUMERIC) {
    // formData.setName(String.valueOf((long)
    // row.getCell(0).getNumericCellValue()));
    // } else {
    // formData.setName(row.getCell(0).getStringCellValue());
    // }
    // }

    // if (row.getCell(1) == null) {
    // formData.setPhone("");
    // } else {
    // if (row.getCell(1).getCellType() == CellType.NUMERIC) {
    // formData.setPhone(String.valueOf((long)
    // row.getCell(1).getNumericCellValue()));
    // } else {
    // formData.setPhone(row.getCell(1).getStringCellValue());
    // }
    // }

    // if (row.getCell(2) == null) {
    // formData.setAddr3("");
    // } else {
    // if (row.getCell(2).getCellType() == CellType.NUMERIC) {
    // formData.setAddr3(String.valueOf((long)
    // row.getCell(2).getNumericCellValue()));
    // } else {
    // formData.setAddr3(row.getCell(2).getStringCellValue());
    // }
    // }

    // if (row.getCell(3) == null) {
    // formData.setAddr1("");
    // } else {
    // if (row.getCell(3).getCellType() == CellType.NUMERIC) {
    // formData.setAddr1(String.valueOf((long)
    // row.getCell(3).getNumericCellValue()));
    // } else {
    // formData.setAddr1(row.getCell(3).getStringCellValue());
    // }
    // }

    // if (row.getCell(4) == null) {
    // formData.setAddr2("");
    // } else {
    // if (row.getCell(4).getCellType() == CellType.NUMERIC) {
    // formData.setAddr2(String.valueOf((long)
    // row.getCell(4).getNumericCellValue()));
    // } else {
    // formData.setAddr2(row.getCell(4).getStringCellValue());
    // }
    // }

    // if (row.getCell(5) == null) {
    // formData.setNote("");
    // } else {
    // if (row.getCell(5).getCellType() == CellType.NUMERIC) {
    // formData.setNote(String.valueOf((long)
    // row.getCell(5).getNumericCellValue()));
    // } else {
    // formData.setNote(row.getCell(5).getStringCellValue());
    // }
    // }

    // if (!formData.getName().equals("") && !formData.getPhone().equals("")
    // && !formData.getAddr1().equals("") && !formData.getAddr3().equals("")) {
    // overlap = new ArrayList<HashMap<String, Object>>();
    // // 중복검사
    // overlap = service.insItemOL(formData);

    // System.out.println(formData);

    // if (overlap.size() == 0) {
    // System.out.println("/admin/insItem/post");

    // service.insItemPost(formData);
    // result.put("result", 200);
    // } else {
    // result.put("result", 400);
    // }
    // }
    // }
    // }
    // }

    // result.put("result", 200);
    // transaction.commit();
    // } catch (Exception e) {
    // result.put("result", 400);
    // transaction.rollback();
    // throw e;
    // } finally {
    // transaction.end();
    // }

    // return result;
    // }

    @PostMapping("/insItem/download")
    public HashMap<String, Object> insItemDownload(
            HttpSession session, InsItemDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/insItem/download");

        result.put("data", service.insItemDownload(formData));

        return result;
    }
}
