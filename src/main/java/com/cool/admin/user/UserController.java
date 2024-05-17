package com.cool.admin.user;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.AdminDto;
import com.cool.dto.LoginDto;
import com.cool.utills.CommonUtills;
import com.cool.utills.MyBatisTransactionManager;
import com.cool.utills.MyBatisTransactionManagerBatch;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/user")
@RestController
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    ApplicationContext applicationContext;

    public MyBatisTransactionManager getTransactionManager() {
        return applicationContext.getBean(MyBatisTransactionManager.class);
    }

    public MyBatisTransactionManagerBatch getTransactionManagerBatch() {
        return applicationContext.getBean(MyBatisTransactionManagerBatch.class);
    }

    @GetMapping
    public HashMap<String, Object> userList(
            HttpSession session, AdminDto formData) throws Exception {
        System.out.println("/admin/user");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.userCnt(formData));

        result.put("data", service.userList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{id}")
    public HashMap<String, Object> userNo(@PathVariable String id) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/user/{id}");

        AdminDto formData = new AdminDto();
        formData.setAdmin_no(id);

        result.put("data", service.userNo(formData));

        return result;
    }

    @PostMapping
    public HashMap<String, Object> userPost(
            HttpSession session, AdminDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/user/post");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.userOL(formData);

        if (overlap.size() == 0) {
            formData.setPw(CommonUtills.hashPassword(formData.getPw()));
            service.userPost(formData);

            for (int i = 1; i <= 18; i++) {
                formData.setAces_no(Integer.toString(i));

                service.userAcesPost(formData);
            }

            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/put")
    public HashMap<String, Object> userPut(
            HttpSession session, AdminDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/user/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        // 중복검사
        List<HashMap<String, Object>> overlap = service.userOL(formData);

        if (overlap.size() == 0) {
            formData.setPw(CommonUtills.hashPassword(formData.getPw()));
            service.userPut(formData);
            result.put("result", 200);
        } else {
            // throw new Exception(); // 강제 에러 발생
            result.put("result", 400);
        }

        return result;
    }

    @PostMapping("/delete")
    public HashMap<String, Object> userDelete(
            HttpSession session, AdminDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();

        System.out.println("/admin/user/delete");

        String no = formData.getAdmin_no();

        if (no.indexOf(",") == -1) {
            service.userDelete(formData);
        } else {
            for (int i = 0; i < no.split(",").length; i++) {
                formData.setAdmin_no(no.split(",")[i]);
                service.userDelete(formData);
            }
        }

        result.put("result", 200);

        return result;
    }

    @GetMapping("/aces")
    public HashMap<String, Object> asUserAces(
            HttpSession session, AdminDto formData) throws Exception {
        System.out.println("/admin/user/aces");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setUsePageNavy(false);
        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        result.put("data", (service.userList(formData)));

        return result;
    }

    @GetMapping("/aces/{admin_no}")
    public HashMap<String, Object> asUserAcesNo(
            @PathVariable String admin_no, HttpSession session, AdminDto formData) throws Exception {
        System.out.println("/admin/user/aces/{admin_no}");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setAdmin_no(admin_no);

        result.put("data", (service.userAcesNo(formData)));

        return result;
    }

    @PostMapping("/aces/put")
    public HashMap<String, Object> userAcesPut(
            HttpSession session, AdminDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/user/aces/put");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        service.userAcesDelete(formData);

        String aces_no_list = formData.getAces_no_list();

        for (int i = 0; i < aces_no_list.split(",").length; i++) {
            formData.setAces_no(aces_no_list.split(",")[i]);
            service.userAcesPost(formData);
        }

        result.put("result", 200);

        return result;
    }
}
