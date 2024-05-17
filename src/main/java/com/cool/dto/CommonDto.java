package com.cool.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonDto extends PageInfo {
    private String cdate;
    @JsonIgnore
    private String is_delete;
}
