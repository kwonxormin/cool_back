package com.cool.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AjaxResponseData {
    private int code;
    private String msg;
    private Object data;

    @Builder
    public AjaxResponseData(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}