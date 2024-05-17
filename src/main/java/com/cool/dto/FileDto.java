package com.cool.dto;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("FileDto")
public class FileDto {
    private String file_no;
    private String upload_no;
    private String upload_table;
    private String upload_name;
    private byte[] upload_file;
}
