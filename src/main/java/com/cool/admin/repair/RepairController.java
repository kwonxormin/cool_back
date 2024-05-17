package com.cool.admin.repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cool.dto.LoginDto;
import com.cool.dto.RepairDto;
import com.cool.utills.CommonUtills;
import com.cool.utills.MyBatisTransactionManager;
import com.cool.utills.MyBatisTransactionManagerBatch;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/repair")
@RestController
public class RepairController {
    @Autowired
    RepairService service;

    @Autowired
    ApplicationContext applicationContext;

    public MyBatisTransactionManager getTransactionManager() {
        return applicationContext.getBean(MyBatisTransactionManager.class);
    }

    public MyBatisTransactionManagerBatch getTransactionManagerBatch() {
        return applicationContext.getBean(MyBatisTransactionManagerBatch.class);
    }

    @GetMapping
    public HashMap<String, Object> repairList(
            HttpSession session, RepairDto formData) throws Exception {
        System.out.println("/admin/repair");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.repairCnt(formData));

        result.put("data", service.repairList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> repairNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/repair/{id}");

        RepairDto formData = new RepairDto();
        formData.setRepair_no(id);

        result.put("data", service.repairNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> repairPost(
            HttpSession session, RepairDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/repair/post");

        try {
            if (session.getAttribute("admin") == null) {
                result.put("message", "로그인 후 이용 바랍니다.");
                return result;
            }

            formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

            formData.setPw(CommonUtills.hashPassword(formData.getPw()));
            service.repairPost(formData);
            result.put("result", 200);
        } catch (DuplicateKeyException e) {
            System.out.println(e);
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> repairPut(
            HttpSession session, RepairDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/repair/put");

        try {
            if (session.getAttribute("admin") == null) {
                result.put("message", "로그인 후 이용 바랍니다.");
                return result;
            }

            formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

            if (formData.getPw() != null && !formData.getPw().equals("")) {
                formData.setPw(CommonUtills.hashPassword(formData.getPw()));
            }

            service.repairPut(formData);
            result.put("result", 200);
        } catch (DuplicateKeyException e) {
            System.out.println(e);
            result.put("result", 400);
        }

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> repairDelete(
            HttpSession session, RepairDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/repair/delete");

        String no = formData.getRepair_no();

        if (no.indexOf(",") == -1) {
            service.repairDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setRepair_no(no.split(",")[i]);
                service.repairDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }

    @PostMapping("/repair/upload")
    public HashMap<String, Object> repairUpload(
            HttpSession session, RepairDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<HashMap<String, Object>> overlap = new ArrayList<HashMap<String, Object>>();
        MultipartHttpServletRequest multipartRequest = formData.getMultipartRequest();
        Iterator<String> itr = multipartRequest.getFileNames();
        MyBatisTransactionManager transaction = getTransactionManager();

        try {
            System.out.println("/admin/repair/upload");

            if (session.getAttribute("admin") == null) {
                result.put("message", "로그인 후 이용 바랍니다.");
                return result;
            }

            formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

            transaction.start();

            while (itr.hasNext()) { // 파일을 하나씩 불러온다.
                MultipartFile file = multipartRequest.getFile(itr.next());
                String extension = FileNameUtils.getExtension(file.getOriginalFilename());

                if (extension.equals("xlsx") || extension.equals("xls")) {
                    Workbook workbook = null;

                    if (extension.equals("xlsx")) {
                        workbook = new XSSFWorkbook(file.getInputStream());
                    } else if (extension.equals("xls")) {
                        workbook = new HSSFWorkbook(file.getInputStream());
                    }

                    Sheet worksheet = workbook.getSheetAt(0);

                    for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                        Row row = worksheet.getRow(i);

                        if (row.getCell(0) == null) {
                            formData.setName("");
                        } else {
                            if (row.getCell(0).getCellType() == CellType.NUMERIC) {
                                formData.setName(String.valueOf((long) row.getCell(0).getNumericCellValue()));
                            } else {
                                formData.setName(row.getCell(0).getStringCellValue());
                            }
                        }

                        if (row.getCell(1) == null) {
                            formData.setId("");
                        } else {
                            if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                                formData.setId(String.valueOf((long) row.getCell(1).getNumericCellValue()));
                            } else {
                                formData.setId(row.getCell(1).getStringCellValue());
                            }
                        }

                        if (row.getCell(2) == null) {
                            formData.setPw("");
                        } else {
                            if (row.getCell(2).getCellType() == CellType.NUMERIC) {
                                formData.setPw(String.valueOf((long) row.getCell(2).getNumericCellValue()));
                            } else {
                                formData.setPw(row.getCell(2).getStringCellValue());
                            }
                        }

                        if (row.getCell(3) == null) {
                            formData.setNote("");
                        } else {
                            if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                                formData.setNote(String.valueOf((long) row.getCell(3).getNumericCellValue()));
                            } else {
                                formData.setNote(row.getCell(3).getStringCellValue());
                            }
                        }

                        if (!formData.getId().equals("") && !formData.getPw().equals("")) {
                            overlap = new ArrayList<HashMap<String, Object>>();
                            // 중복검사
                            overlap = service.repairOL(formData);

                            System.out.println(formData);

                            if (overlap.size() == 0) {
                                System.out.println("/cool/admin/repair/post");

                                service.repairPost(formData);
                                result.put("result", 200);
                            } else {
                                // throw new Exception(); // 강제 에러 발생
                                result.put("result", 400);
                            }
                        }
                    }
                }
            }

            result.put("result", 200);
            transaction.commit();
        } catch (Exception e) {
            result.put("result", 400);
            transaction.rollback();
            throw e;
        } finally {
            transaction.end();
        }

        return result;
    }

    @PostMapping("/repair/download")
    public HashMap<String, Object> repairDownload(
            HttpSession session, RepairDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/cool/admin/repair/download");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        result.put("data", service.repairDownload(formData));

        return result;
    }
}
