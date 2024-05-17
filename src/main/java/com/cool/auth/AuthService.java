package com.cool.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cool.dto.AjaxResponseData;
import com.cool.dto.LoginDto;
import com.cool.utills.CommonUtills;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    AuthMapper authMapper;

    // 로그인
    public ResponseEntity<AjaxResponseData> login(HttpSession session, HttpServletRequest request,
            HttpServletResponse response, LoginDto.request formData) {
        try {
            System.out.println("/auth/login");

            if (formData.getPw() != null && !formData.getPw().equals("")) {
                formData.setPw(CommonUtills.hashPassword(formData.getPw()));
            }

            LoginDto.adminResponse adminResponse = null;
            LoginDto.cusResponse cusResponse = null;
            LoginDto.repairResponse repairResponse = null;
            String auth = formData.getAuth();

            switch (auth) {
                case "admin":
                    adminResponse = authMapper.loginAdmin(formData);

                    if (adminResponse != null) {
                        // 세션 저장
                        session.setAttribute(auth, adminResponse);
                        session.setMaxInactiveInterval(24 * 60 * 60);

                        // 쿠키 저장
                        Cookie cookie = new Cookie(auth, adminResponse.getId());
                        cookie.setMaxAge(60 * 60);
                        response.addCookie(cookie);
                    } else {
                        return ResponseEntity.ok()
                                .body(AjaxResponseData.builder().msg("로그인 정보가 일치하지 않습니다.").code(202).build());
                    }
                    break;
                case "adminApp":
                    adminResponse = authMapper.loginAdmin(formData);

                    if (adminResponse != null) {
                        // 세션 저장
                        session.setAttribute(auth, adminResponse);
                        session.setMaxInactiveInterval(24 * 60 * 60);

                        // 쿠키 저장
                        Cookie cookie = new Cookie(auth, adminResponse.getId());
                        cookie.setMaxAge(60 * 60);
                        response.addCookie(cookie);
                    } else {
                        return ResponseEntity.ok()
                                .body(AjaxResponseData.builder().msg("로그인 정보가 일치하지 않습니다.").code(202).build());
                    }
                    break;
                case "cus":
                    cusResponse = authMapper.loginCus(formData);

                    if (cusResponse != null) {
                        // 세션 저장
                        session.setAttribute(auth, cusResponse);
                        session.setMaxInactiveInterval(24 * 60 * 60);

                        // 쿠키 저장
                        Cookie cookie = new Cookie(auth, cusResponse.getPhone());
                        cookie.setMaxAge(60 * 60);
                        response.addCookie(cookie);
                    } else {
                        return ResponseEntity.ok()
                                .body(AjaxResponseData.builder().msg("로그인 정보가 일치하지 않습니다.").code(202).build());
                    }
                    break;
                case "repair":
                    System.out.println(formData.getPw());
                    repairResponse = authMapper.loginRepair(formData);

                    if (repairResponse != null) {
                        // 세션 저장
                        session.setAttribute(auth, repairResponse);
                        session.setMaxInactiveInterval(24 * 60 * 60);

                        // 쿠키 저장
                        Cookie cookie = new Cookie(auth, repairResponse.getId());
                        cookie.setMaxAge(60 * 60);
                        response.addCookie(cookie);
                    } else {
                        return ResponseEntity.ok()
                                .body(AjaxResponseData.builder().msg("로그인 정보가 일치하지 않습니다.").code(202).build());
                    }
                    break;
            }

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(AjaxResponseData.builder().msg(e.toString()).code(500).build());
        }

        return ResponseEntity.ok().body(AjaxResponseData.builder().msg("로그인 성공").code(200).build());
    }

    // 로그아웃
    public ResponseEntity<AjaxResponseData> logout(HttpServletRequest request, HttpServletResponse response,
            String auth) {
        try {
            System.out.println("/auth/logout");

            // 세션에서 로그인 정보 삭제
            HttpSession session = request.getSession(false);

            if (session != null) {
                switch (auth) {
                    case "admin":
                        session.removeAttribute("admin_info");
                        break;
                    case "user":
                        session.removeAttribute("user_info");
                        break;
                    case "repair":
                        session.removeAttribute("repair_info");
                        break;
                }

                session.invalidate();
            }

            // 쿠키에서 유저 정보 삭제
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(auth)) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body(AjaxResponseData.builder().msg(e.toString()).code(500).build());
        }

        return ResponseEntity.ok().body(AjaxResponseData.builder().msg("로그아웃 성공").code(200).build());
    }

    // 로그인 상태
    public ResponseEntity<AjaxResponseData> status(HttpServletRequest request, String auth) {
        System.out.println("/auth/status");

        String MSG = "로그아웃 상태";
        int CODE = 202;
        Object DATA = null;

        try {
            HttpSession session = request.getSession(false);

            if (session != null) {
                Object info = session.getAttribute(auth);

                if (info != null) {
                    Cookie[] cookies = request.getCookies();

                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if (cookie.getName().equals(auth)) {
                                cookie.setMaxAge(60 * 60);
                                CODE = 200;
                                MSG = "로그인 상태";
                                DATA = info;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            CODE = 500;
            return ResponseEntity.badRequest().body(AjaxResponseData.builder().msg(e.toString()).code(CODE).build());
        }

        return ResponseEntity.ok().body(AjaxResponseData.builder().data(DATA).msg(MSG).code(CODE).build());
    }
}