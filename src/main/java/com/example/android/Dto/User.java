package com.example.android.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String u_id;
    private String u_email;
    private String u_pwd;
    private String u_phone;
    private Date u_reg_dt;
}
