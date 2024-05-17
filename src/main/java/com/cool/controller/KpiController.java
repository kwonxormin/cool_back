package com.cool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.AjaxResponseData;
import com.cool.dto.KpiDto;
import com.cool.service.KpiService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/kpi2") // 추후 기능별 나눌때 수정 @RequestMapping("/kpi"))
public class KpiController {
    @Autowired
    KpiService kpiService;

    // 메인 홈 KPI 조회
    @GetMapping
    public ResponseEntity<AjaxResponseData> getKpi(HttpSession session, KpiDto.getRequest formData) {
        return kpiService.getKpi(session, formData);
    }

    // // 관리자 로그아웃
    // @GetMapping("/logout")
    // public ResponseEntity<AjaxResponseData> logout(HttpServletRequest request,
    // HttpServletResponse response, @ModelAttribute("auth") String auth) {
    // return authService.logout(request, response, auth);
    // }

    // // 관리자 로그인 상태 확인
    // @GetMapping("/status")
    // public ResponseEntity<AjaxResponseData> status(HttpServletRequest request,
    // @ModelAttribute("auth") String auth) {
    // return authService.status(request, auth);
    // }
}
