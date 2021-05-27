package com.example.android.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class Board {
    private int b_no;
    private char cat_cd;
    private String b_title;
    private String b_content;
    private int b_visite;
    private LocalDateTime b_dtt;
    private String u_id;

//    private MultipartFile[] file;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime time;
}
