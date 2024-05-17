package com.cool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cool.dto.AjaxResponseData;
import com.cool.dto.KpiDto;
import com.cool.dto.LoginDto;
import com.cool.mapper.KpiMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
public class KpiService {
    @Autowired
    KpiMapper kpiMapper;

    // 메인 홈 KPI 조회
    public ResponseEntity<AjaxResponseData> getKpi(HttpSession session, KpiDto.getRequest formData) {
        System.out.println("/kpi");

        formData.setCmpn_no(((LoginDto.adminResponse) session.getAttribute("admin")).getCmpn_no());

        System.out.println(formData);

        KpiDto.getResponse getResponse = new KpiDto.getResponse();

        try {
            YearMonth yearMonth = YearMonth.parse(formData.getDate());
            formData.setSdate(yearMonth.atDay(1).toString());
            formData.setEdate(yearMonth.atEndOfMonth().toString() + " 23:59:59");

            System.out.println(formData);

            

            // body.put("status", "1");
            // result.put("status_1", coolAdminService.kpiAs(body).get(0).get("cnt"));

            // body.put("status", "2");
            // result.put("status_2", coolAdminService.kpiAs(body).get(0).get("cnt"));

            // body.put("status", "5");
            // result.put("status_5", coolAdminService.kpiAs(body).get(0).get("cnt"));

            // body.put("status", "6");
            // result.put("status_6", coolAdminService.kpiAs(body).get(0).get("cnt"));

            // kpiTime = coolAdminService.kpiTime(body);
            // kpiDate = coolAdminService.kpiDate(body);

            // if (kpiTime.get(0) != null) {
            //     result.put("as_time", kpiTime.get(0).get("as_time"));
            // } else {
            //     result.put("as_time", 0);
            // }

            // if (kpiDate.get(0) != null) {
            //     result.put("as_date", kpiDate.get(0).get("as_date"));
            // } else {
            //     result.put("as_date", 0);
            // }
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(AjaxResponseData.builder().msg(e.toString()).code(500).build());
        }

        return ResponseEntity.ok().body(AjaxResponseData.builder().data(getResponse).msg("조회 성공").code(200).build());
    }
}