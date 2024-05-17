package com.cool.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cool.dto.AjaxResponseData;
import com.cool.dto.LoginDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    @Autowired
	AuthService authService;

    // 관리자 로그인
    @PostMapping("/login")
    public ResponseEntity<AjaxResponseData> login(HttpSession session, HttpServletRequest request, HttpServletResponse response, LoginDto.request formData) {
        return authService.login(session, request, response, formData);
    }

    // 관리자 로그아웃
    @GetMapping("/logout")
    public ResponseEntity<AjaxResponseData> logout(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("auth") String auth) {
        return authService.logout(request, response, auth);
    }

    // 관리자 로그인 상태 확인
    @GetMapping("/status")
    public ResponseEntity<AjaxResponseData> status(HttpServletRequest request, @ModelAttribute("auth") String auth) {
        return authService.status(request, auth);
    }
}
