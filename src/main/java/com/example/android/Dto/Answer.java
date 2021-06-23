package com.example.android.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class Answer {
    private String q_cd;
    private String ans_content;
    private Date ans_dt;
    private String ans_writer;
}
