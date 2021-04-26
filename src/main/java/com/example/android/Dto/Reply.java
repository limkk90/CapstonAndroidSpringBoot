package com.example.android.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Reply {
    private LocalDateTime r_dtt; // 댓글번호
    private String r_content; // 내용
    private String r_writer; // 작성자
    private Date r_dt; // 작성일
    private String b_no; // 글번호
}
