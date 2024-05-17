package com.cool.admin.glasses;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.GlassesDto;
import com.cool.dto.LoginDto;
import com.cool.media.RtcTokenBuilder;
import com.cool.media.RtcTokenBuilder.Role;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/glasses")
@RestController
public class GlassesController {
    @Autowired
    GlassesService service;

    @GetMapping
    public HashMap<String, Object> glassesList(
            HttpSession session, GlassesDto formData) throws Exception {
        System.out.println("/admin/glasses");

        HashMap<String, Object> result = new HashMap<String, Object>();

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());
        formData.setTotalCnt(service.glassesCnt(formData));

        result.put("data", service.glassesList(formData));
        result.put("pageInfo", formData.getPageInfo());

        return result;
    }

    @GetMapping("/{glasses_no}")
    public HashMap<String, Object> glassesNo(
            @PathVariable String glasses_no, GlassesDto formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        HashMap<String, Object> glassesNo = new HashMap<String, Object>();
        System.out.println("/admin/glasses/{glasses_no}");

        String appId = "b2733c18206a4c0cabb57040c800b7e2";
        String appCertificate = "8ef574dc97ad4aa4b7461bfbfcbc0274";
        String userAccount = "";
        int expirationTimeInSeconds = 86400;
        int uid = 0;
        String channelName = glasses_no + glasses_no;

        formData.setGlasses_no(glasses_no);

        result.put("appId", appId);

        if (glasses_no.length() < 2) {
            channelName = "0" + glasses_no + "0" + glasses_no;
        }

        glassesNo = service.glassesNo(formData);
        result.put("data", glassesNo);

        String e_date = String.valueOf(glassesNo.get("e_date"));

        Calendar cal1 = Calendar.getInstance();
        cal1.set(Integer.parseInt(e_date.split("\\.")[0]), Integer.parseInt(e_date.split("\\.")[1]) - 1,
                Integer.parseInt(e_date.split("\\.")[2]));

        Calendar cal2 = Calendar.getInstance();

        if (cal1.compareTo(cal2) == -1 || glassesNo.get("token") == null
                || glassesNo.get("token").toString().equals("")) {
            System.out.println("토큰 생성");
            RtcTokenBuilder token = new RtcTokenBuilder();
            int timestamp = (int) (System.currentTimeMillis() / 1000 + expirationTimeInSeconds);
            String results = token.buildTokenWithUserAccount(appId, appCertificate,
                    channelName, userAccount, Role.Role_Publisher, timestamp);

            results = token.buildTokenWithUid(appId, appCertificate,
                    channelName, uid, Role.Role_Publisher, timestamp);

            formData.setChannelName(channelName);
            formData.setToken(results);

            System.out.println("/admin/glasses/tokenPut");

            service.glassesTokenPut(formData);
            result.put("data", service.glassesNo(formData));
        } else {
            System.out.println("기존 토큰");
        }

        return result;
    }

    // @GetMapping("/{id}")
    // public HashMap<String, Object> glassesNo(@PathVariable String id) throws
    // Exception {
    // HashMap<String, Object> result = new HashMap<String, Object>();
    // System.out.println("/admin/glasses/{id}");

    // GlassesDto formData = new GlassesDto();
    // formData.setGlasses_no(id);

    // result.put("data", service.glassesNo(formData));

    // return result;
    // }

    // @GetMapping("/as")
    // public HashMap<String, Object> glassesAsList(
    // HttpSession session, AsDto formData) throws Exception {
    // System.out.println("/admin/glasses/as");

    // HashMap<String, Object> result = new HashMap<String, Object>();

    // if (session.getAttribute("admin") == null) {
    // result.put("message", "로그인 후 이용 바랍니다.");
    // return result;
    // }

    // formData.setCmpn_no(((LoginDto.adminResponse)
    // session.getAttribute("admin")).getCmpn_no());
    // formData.setTotalCnt(service.glassesAsCnt(formData));

    // result.put("data", service.glassesAsList(formData));
    // result.put("pageInfo", formData.getPageInfo());

    // return result;
    // }

    // @PostMapping
    // public HashMap<String, Object> glassesPost(
    // HttpSession session, GlassesDto formData) throws Exception {
    // HashMap<String, Object> result = new HashMap<String, Object>();
    // System.out.println("/admin/glasses/post");

    // if (session.getAttribute("admin") == null) {
    // result.put("message", "로그인 후 이용 바랍니다.");
    // return result;
    // }

    // formData.setCmpn_no(((LoginDto.adminResponse)
    // session.getAttribute("admin")).getCmpn_no());

    // // 중복검사
    // List<HashMap<String, Object>> overlap = service.glassesOL(formData);

    // if (overlap.size() == 0) {
    // service.glassesPost(formData);
    // result.put("result", 200);
    // } else {
    // // throw new Exception(); // 강제 에러 발생
    // result.put("result", 400);
    // }

    // return result;
    // }

    // @PostMapping("/put")
    // public HashMap<String, Object> glassesPut(
    // HttpSession session, GlassesDto formData) throws Exception {
    // HashMap<String, Object> result = new HashMap<String, Object>();
    // System.out.println("/admin/glasses/put");

    // if (session.getAttribute("admin") == null) {
    // result.put("message", "로그인 후 이용 바랍니다.");
    // return result;
    // }

    // formData.setCmpn_no(((LoginDto.adminResponse)
    // session.getAttribute("admin")).getCmpn_no());

    // // 중복검사
    // List<HashMap<String, Object>> overlap = service.glassesOL(formData);

    // if (overlap.size() == 0) {
    // service.glassesPut(formData);
    // result.put("result", 200);
    // } else {
    // // throw new Exception(); // 강제 에러 발생
    // result.put("result", 400);
    // }

    // return result;
    // }

    // @PostMapping("/delete")
    // public HashMap<String, Object> glassesDelete(
    // HttpSession session, GlassesDto formData) throws Exception {
    // HashMap<String, Object> result = new HashMap<String, Object>();

    // System.out.println("/admin/glasses/delete");

    // String no = formData.getGlasses_no();

    // if (no.indexOf(",") == -1) {
    // service.glassesDelete(formData);
    // } else {
    // for (int i = 0; i < no.split(",").length; i++) {
    // formData.setGlasses_no(no.split(",")[i]);
    // service.glassesDelete(formData);
    // }
    // }

    // result.put("result", 200);

    // return result;
    // }
}
