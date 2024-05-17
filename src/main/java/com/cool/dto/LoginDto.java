package com.cool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LoginDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class request {
        private String id;
        private String pw;
        private String phone;
        private String auth;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class adminResponse {
        private String id;
        private int cmpn_no;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class cusResponse {
        private String cus_no;
        private String phone;
        private int cmpn_no;
        private String name;
        private String note;
        private String addr1;
        private String addr2;
        private String addr3;
        private String cmpn_phone;
        private String cmpn_name;
        private String cmpn_lat;
        private String cmpn_lon;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class repairResponse {
        private String id;
        private int cmpn_no;
    }
}
