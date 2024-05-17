package com.cool.admin.as;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.admin.cus.CusService;
import com.cool.admin.insItem.InsItemService;
import com.cool.admin.repair.RepairService;
import com.cool.dto.AsDto;
import com.cool.dto.CusDto;
import com.cool.dto.FileDto;
import com.cool.dto.InsItemDto;
import com.cool.dto.LoginDto;
import com.cool.dto.RepairDto;
import com.cool.utills.MyBatisTransactionManager;
import com.cool.utills.MyBatisTransactionManagerBatch;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/as")
@RestController
public class AsController {
    @Autowired
    AsService service;

    @Autowired
    CusService cusService;

    @Autowired
    RepairService repairService;

    @Autowired
    InsItemService insItemService;

    @Autowired
    ApplicationContext applicationContext;

    public MyBatisTransactionManager getTransactionManager() {
        return applicationContext.getBean(MyBatisTransactionManager.class);
    }

    public MyBatisTransactionManagerBatch getTransactionManagerBatch() {
        return applicationContext.getBean(MyBatisTransactionManagerBatch.class);
    }

    @GetMapping
    public HashMap<String, Object> asList(
            HttpSession session, AsDto formData) throws Exception {
        System.out.println("/admin/as");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.asCnt(formData));

        result.put("data", service.asList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> asNo(@PathVariable String id, HttpSession session) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> asNo = new HashMap<String, Object>();
        System.out.println("/admin/as/{id}");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        AsDto formData = new AsDto();
        formData.setAs_no(id);

        asNo = service.asNo(formData);

        result.put("data", asNo);

        FileDto fileDto = new FileDto();
        fileDto.setUpload_no(asNo.get("as_no").toString());
        fileDto.setUpload_table("tb_as");

        fileDto.setUpload_name("img_s");
        result.put("img_s", service.fileList(fileDto));

        fileDto.setUpload_name("img_e");
        result.put("img_e", service.fileList(fileDto));

        fileDto.setUpload_name("img_c");
        result.put("img_c", service.fileList(fileDto));

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        result.put("cmpnList", service.asCmpnList(formData));

        return result;
    }

    @GetMapping("/cus")
    public HashMap<String, Object> asCusList(
            HttpSession session, CusDto formData) throws Exception {
        System.out.println("/admin/as/repair");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setUsePageNavy(false);
        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        result.put("data", (cusService.cusList(formData)));

        return result;
    }

    @GetMapping("/insItem/{cus_no}")
    public HashMap<String, Object> asInsItemList(
            @PathVariable String cus_no, HttpSession session, InsItemDto formData) throws Exception {
        System.out.println("/admin/as/insItem");

        HashMap<String, Object> result = new HashMap<String, Object>();

        formData.setCus_no(cus_no);

        result.put("data", service.asInsItemList(formData));

        return result;
    }

    @GetMapping("/repair/{id}")
    public HashMap<String, Object> asRepairList(
            @PathVariable int id, HttpSession session, RepairDto formData) throws Exception {
        System.out.println("/admin/as/repair");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setUsePageNavy(false);
        formData.setCmpn_no(id);

        result.put("data", (repairService.repairList(formData)));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> asPost(
            HttpSession session, AsDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/as/post");

        service.asPost(formData);

        FileDto fileDto = new FileDto();
        fileDto.setUpload_no(formData.getAs_no());

        if (formData.getImg_s().get(0).getSize() > 0) {
            fileDto.setUpload_table("tb_as");
            fileDto.setUpload_name("img_s");

            for (int i = 0; i < formData.getImg_s().size(); i++) {
                fileDto.setUpload_file(formData.getImg_s().get(i).getBytes());

                service.filePost(fileDto);
            }
        }

        if (formData.getImg_e().get(0).getSize() > 0) {
            fileDto.setUpload_table("tb_as");
            fileDto.setUpload_name("img_e");

            for (int i = 0; i < formData.getImg_e().size(); i++) {
                fileDto.setUpload_file(formData.getImg_e().get(i).getBytes());

                service.filePost(fileDto);
            }
        }

        result.put("result", 200);

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> asPut(
            HttpSession session, AsDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/as/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        FileDto fileDto = new FileDto();
        fileDto.setUpload_no(formData.getAs_no());
        fileDto.setUpload_table("tb_as");

        fileDto.setUpload_name("img_e");
        service.fileDelete(fileDto);

        fileDto.setUpload_name("img_s");
        service.fileDelete(fileDto);

        if (formData.getImg_s().get(0).getSize() > 0) {
            fileDto.setUpload_table("tb_as");
            fileDto.setUpload_name("img_s");

            service.fileDelete(fileDto);

            for (int i = 0; i < formData.getImg_s().size(); i++) {
                fileDto.setUpload_file(formData.getImg_s().get(i).getBytes());

                service.filePost(fileDto);
            }
        }

        if (formData.getImg_e().get(0).getSize() > 0) {
            fileDto.setUpload_table("tb_as");
            fileDto.setUpload_name("img_e");

            service.fileDelete(fileDto);

            for (int i = 0; i < formData.getImg_e().size(); i++) {
                fileDto.setUpload_file(formData.getImg_e().get(i).getBytes());

                service.filePost(fileDto);
            }
        }

        service.asPut(formData);
        result.put("result", 200);

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> asDelete(
            HttpSession session, AsDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/as/delete");

        String no = formData.getAs_no();

        if (no.indexOf(",") == -1) {
            FileDto fileDto = new FileDto();
            fileDto.setUpload_no(no);
            fileDto.setUpload_table("tb_as");

            fileDto.setUpload_name("img_e");
            service.fileDelete(fileDto);

            fileDto.setUpload_name("img_s");
            service.fileDelete(fileDto);

            service.asDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setAs_no(no.split(",")[i]);

                FileDto fileDto = new FileDto();
                fileDto.setUpload_no(no.split(",")[i]);
                fileDto.setUpload_table("tb_as");

                fileDto.setUpload_name("img_e");
                service.fileDelete(fileDto);

                fileDto.setUpload_name("img_s");
                service.fileDelete(fileDto);

                service.asDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }

    // @PostMapping("/upload")
    // public HashMap<String, Object> asUpload(
    //         HttpSession session, CusDto formData) throws Exception {
    //     HashMap<String, Object> result = new HashMap<String, Object>();
    //     List<HashMap<String, Object>> overlap = new ArrayList<HashMap<String, Object>>();
    //     MultipartHttpServletRequest multipartRequest = formData.getMultipartRequest();
    //     Iterator<String> itr = multipartRequest.getFileNames();
    //     MyBatisTransactionManager transaction = getTransactionManager();

    //     try {
    //         System.out.println("/admin/as/upload");

    //         if (session.getAttribute("admin") == null) {
    //             result.put("message", "로그인 후 이용 바랍니다.");
    //             return result;
    //         }

    //         formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

    //         transaction.start();

    //         while (itr.hasNext()) { // 파일을 하나씩 불러온다.
    //             MultipartFile file = multipartRequest.getFile(itr.next());
    //             String extension = FileNameUtils.getExtension(file.getOriginalFilename());

    //             if (extension.equals("xlsx") || extension.equals("xls")) {
    //                 Workbook workbook = null;

    //                 if (extension.equals("xlsx")) {
    //                     workbook = new XSSFWorkbook(file.getInputStream());
    //                 } else if (extension.equals("xls")) {
    //                     workbook = new HSSFWorkbook(file.getInputStream());
    //                 }

    //                 Sheet worksheet = workbook.getSheetAt(0);

    //                 for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
    //                     Row row = worksheet.getRow(i);

    //                     if (row.getCell(0) == null) {
    //                         formData.setName("");
    //                     } else {
    //                         if (row.getCell(0).getCellType() == CellType.NUMERIC) {
    //                             formData.setName(String.valueOf((long) row.getCell(0).getNumericCellValue()));
    //                         } else {
    //                             formData.setName(row.getCell(0).getStringCellValue());
    //                         }
    //                     }

    //                     if (row.getCell(1) == null) {
    //                         formData.setPhone("");
    //                     } else {
    //                         if (row.getCell(1).getCellType() == CellType.NUMERIC) {
    //                             formData.setPhone(String.valueOf((long) row.getCell(1).getNumericCellValue()));
    //                         } else {
    //                             formData.setPhone(row.getCell(1).getStringCellValue());
    //                         }
    //                     }

    //                     if (row.getCell(2) == null) {
    //                         formData.setAddr3("");
    //                     } else {
    //                         if (row.getCell(2).getCellType() == CellType.NUMERIC) {
    //                             formData.setAddr3(String.valueOf((long) row.getCell(2).getNumericCellValue()));
    //                         } else {
    //                             formData.setAddr3(row.getCell(2).getStringCellValue());
    //                         }
    //                     }

    //                     if (row.getCell(3) == null) {
    //                         formData.setAddr1("");
    //                     } else {
    //                         if (row.getCell(3).getCellType() == CellType.NUMERIC) {
    //                             formData.setAddr1(String.valueOf((long) row.getCell(3).getNumericCellValue()));
    //                         } else {
    //                             formData.setAddr1(row.getCell(3).getStringCellValue());
    //                         }
    //                     }

    //                     if (row.getCell(4) == null) {
    //                         formData.setAddr2("");
    //                     } else {
    //                         if (row.getCell(4).getCellType() == CellType.NUMERIC) {
    //                             formData.setAddr2(String.valueOf((long) row.getCell(4).getNumericCellValue()));
    //                         } else {
    //                             formData.setAddr2(row.getCell(4).getStringCellValue());
    //                         }
    //                     }

    //                     if (row.getCell(5) == null) {
    //                         formData.setNote("");
    //                     } else {
    //                         if (row.getCell(5).getCellType() == CellType.NUMERIC) {
    //                             formData.setNote(String.valueOf((long) row.getCell(5).getNumericCellValue()));
    //                         } else {
    //                             formData.setNote(row.getCell(5).getStringCellValue());
    //                         }
    //                     }

    //                     if (!formData.getName().equals("") && !formData.getPhone().equals("")
    //                             && !formData.getAddr1().equals("") && !formData.getAddr3().equals("")) {
    //                         overlap = new ArrayList<HashMap<String, Object>>();
    //                         // 중복검사
    //                         overlap = service.cusOL(formData);

    //                         System.out.println(formData);

    //                         if (overlap.size() == 0) {
    //                             System.out.println("/admin/cus/post");

    //                             service.cusPost(formData);
    //                             result.put("result", 200);
    //                         } else {
    //                             result.put("result", 400);
    //                         }
    //                     }
    //                 }
    //             }
    //         }

    //         result.put("result", 200);
    //         transaction.commit();
    //     } catch (Exception e) {
    //         result.put("result", 400);
    //         transaction.rollback();
    //         throw e;
    //     } finally {
    //         transaction.end();
    //     }

    //     return result;
    // }

    // @PostMapping("/download")
    // public HashMap<String, Object> cusDownload(
    //         HttpSession session, CusDto formData) throws Exception {
    //     HashMap<String, Object> result = new HashMap<String, Object>();

    //     System.out.println("/admin/as/download");

    //     result.put("data", service.cusDownload(formData));

    //     return result;
    // }
}
