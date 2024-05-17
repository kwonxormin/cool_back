package com.cool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class KpiDto {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getRequest {
        private int cmpn_no;
        private String date;
        private String sdate;
        private String edate;
        private String status;
        private String status1;
        private String status2;
        private String status5;
        private String status6;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class getResponse {
        private int cmpn_no;
        private String date;
    }
}
