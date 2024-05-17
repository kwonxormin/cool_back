package com.cool.admin.cus;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cool.dto.AsDto;
import com.cool.dto.CusDto;
import com.cool.dto.LoginDto;
import com.cool.utills.MyBatisTransactionManager;
import com.cool.utills.MyBatisTransactionManagerBatch;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/cus")
@RestController
public class CusController {
    @Autowired
    CusService service;

    @Autowired
    ApplicationContext applicationContext;

    public MyBatisTransactionManager getTransactionManager() {
        return applicationContext.getBean(MyBatisTransactionManager.class);
    }

    public MyBatisTransactionManagerBatch getTransactionManagerBatch() {
        return applicationContext.getBean(MyBatisTransactionManagerBatch.class);
    }

    @GetMapping
    public HashMap<String, Object> cusList(
            HttpSession session, CusDto formData) throws Exception {
        System.out.println("/admin/cus");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.cusCnt(formData));

        result.put("data", service.cusList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> cusNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/cus/{id}");

        CusDto formData = new CusDto();
        formData.setCus_no(id);

        result.put("data", service.cusNo(formData));

        return result;
    }

    @GetMapping("/as")
    public HashMap<String, Object> cusAsList(
            HttpSession session, AsDto formData) throws Exception {
        System.out.println("/admin/cus/as");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.cusAsCnt(formData));

        result.put("data", service.cusAsList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @PostMapping
    public HashMap<String, Object> cusPost(
            HttpSession session, CusDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/cus/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.cusOL(formData);

        if (overlap.size() == 0) {
            service.cusPost(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> cusPut(
            HttpSession session, CusDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/cus/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.cusOL(formData);

        if (overlap.size() == 0) {
            service.cusPut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> cusDelete(
            HttpSession session, CusDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/cus/delete");

        String no = formData.getCus_no();

        if (no.indexOf(",") == -1) {
            service.cusDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setCus_no(no.split(",")[i]);
                service.cusDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }

    @PostMapping("/cus/upload")
    public HashMap<String, Object> cusUpload(
            HttpSession session, CusDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<HashMap<String, Object>> overlap = new ArrayList<HashMap<String, Object>>();
        MultipartHttpServletRequest multipartRequest = formData.getMultipartRequest();
        Iterator<String> itr = multipartRequest.getFileNames();
        MyBatisTransactionManager transaction = getTransactionManager();

        try {
            System.out.println("/admin/cus/upload");

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
                            formData.setPhone("");
                        } else {
                            if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                                formData.setPhone(String.valueOf((long) row.getCell(1).getNumericCellValue()));
                            } else {
                                formData.setPhone(row.getCell(1).getStringCellValue());
                            }
                        }

                        if (row.getCell(2) == null) {
                            formData.setAddr3("");
                        } else {
                            if (row.getCell(2).getCellType() == CellType.NUMERIC) {
                                formData.setAddr3(String.valueOf((long) row.getCell(2).getNumericCellValue()));
                            } else {
                                formData.setAddr3(row.getCell(2).getStringCellValue());
                            }
                        }

                        if (row.getCell(3) == null) {
                            formData.setAddr1("");
                        } else {
                            if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                                formData.setAddr1(String.valueOf((long) row.getCell(3).getNumericCellValue()));
                            } else {
                                formData.setAddr1(row.getCell(3).getStringCellValue());
                            }
                        }

                        if (row.getCell(4) == null) {
                            formData.setAddr2("");
                        } else {
                            if (row.getCell(4).getCellType() == CellType.NUMERIC) {
                                formData.setAddr2(String.valueOf((long) row.getCell(4).getNumericCellValue()));
                            } else {
                                formData.setAddr2(row.getCell(4).getStringCellValue());
                            }
                        }

                        if (row.getCell(5) == null) {
                            formData.setNote("");
                        } else {
                            if (row.getCell(5).getCellType() == CellType.NUMERIC) {
                                formData.setNote(String.valueOf((long) row.getCell(5).getNumericCellValue()));
                            } else {
                                formData.setNote(row.getCell(5).getStringCellValue());
                            }
                        }

                        if (!formData.getName().equals("") && !formData.getPhone().equals("")
                                && !formData.getAddr1().equals("") && !formData.getAddr3().equals("")) {
                            overlap = new ArrayList<HashMap<String, Object>>();
                            // 중복검사
                            overlap = service.cusOL(formData);

                            System.out.println(formData);

                            if (overlap.size() == 0) {
                                System.out.println("/admin/cus/post");

                                service.cusPost(formData);
                                result.put("result", 200);
                            } else {
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

    @PostMapping("/cus/download")
    public HashMap<String, Object> cusDownload(
            HttpSession session, CusDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/cus/download");

        result.put("data", service.cusDownload(formData));

        return result;
    }
}
