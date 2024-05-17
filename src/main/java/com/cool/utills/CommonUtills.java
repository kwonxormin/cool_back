package com.cool.utills;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CommonUtills {
    // 비밀번호 해싱 로직
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // 로그인 상태 체크
    public static Object authStatusCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Object result = null;

        if (session != null) {
            Object info = session.getAttribute("info");

            if (info != null) {
                Cookie[] cookies = request.getCookies();

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("biz_no")) {
                            result = info;
                        }
                    }
                }
            }
        }

        return result;
    }

    // 임시 비밀번호 생성
    public static String getRandom(int cnt) {
        String characters = "0123456789";
        StringBuilder randomString = new StringBuilder(cnt);
        Random random = new Random();

        for (int i = 0; i < cnt; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }
}