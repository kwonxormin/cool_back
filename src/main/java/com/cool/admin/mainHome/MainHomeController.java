package com.cool.admin.mainHome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.YearMonth;
import java.util.*;

import com.cool.dto.LoginDto;

import jakarta.servlet.http.HttpSession;

@CrossOrigin("*")
@RequestMapping("/admin/mainHome")
@RestController
public class MainHomeController {
    @Autowired
    MainHomeService service;

    // 메인 홈 - 메인 홈 KPI 조회
    @GetMapping("/kpi")
    public HashMap<String, Object> kpi(
            HttpSession session,
            @RequestParam HashMap<String, Object> formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<HashMap<String, Object>> kpiTime = new ArrayList<HashMap<String, Object>>();
        List<HashMap<String, Object>> kpiDate = new ArrayList<HashMap<String, Object>>();
        System.out.println("/admin/kpi");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.put("cmpn_no", ((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        String date = formData.get("date").toString();

        YearMonth yearMonth = YearMonth.parse(date);
        formData.put("sDate", yearMonth.atDay(1).toString());
        formData.put("eDate", yearMonth.atEndOfMonth().toString() + " 23:59:59");

        formData.put("status", "1");
        result.put("status_1", service.kpiAs(formData).get(0).get("cnt"));

        formData.put("status", "2");
        result.put("status_2", service.kpiAs(formData).get(0).get("cnt"));

        formData.put("status", "5");
        result.put("status_5", service.kpiAs(formData).get(0).get("cnt"));

        formData.put("status", "6");
        result.put("status_6", service.kpiAs(formData).get(0).get("cnt"));

        kpiTime = service.kpiTime(formData);
        kpiDate = service.kpiDate(formData);

        if (kpiTime.get(0) != null) {
            result.put("as_time", kpiTime.get(0).get("as_time"));
        } else {
            result.put("as_time", 0);
        }

        if (kpiDate.get(0) != null) {
            result.put("as_date", kpiDate.get(0).get("as_date"));
        } else {
            result.put("as_date", 0);
        }

        return result;
    }

    // 메인 홈 - 메인 홈 KPI 조회 - 연도별
    @GetMapping("/kpi/dyear")
    public HashMap<String, Object> kpiDyear(
            HttpSession session,
            @RequestParam HashMap<String, Object> formData) throws Exception {
        HashMap<String, Object> result = new HashMap<String, Object>();
        System.out.println("/admin/kpi/dyear");

        if (session.getAttribute("admin") == null) {
            result.put("message", "로그인 후 이용 바랍니다.");
            return result;
        }

        formData.put("cmpn_no", ((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        result.put("data", service.kpiDyear(formData));

        return result;
    }
}
