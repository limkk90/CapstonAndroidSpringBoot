package com.example.android.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String u_id; // 아이디 (아이디 15 + 권한 1)
    private String u_email; // 이메일
    private String u_pwd; // 비밀번호
    private String u_car;
    private String u_s_car;
    private int u_point;
    private Date u_reg_dt; // 가입일
}
