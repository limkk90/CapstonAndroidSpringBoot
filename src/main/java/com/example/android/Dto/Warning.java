package com.example.android.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class Warning {
    private Date w_dt; // 경고일
    private String u_id; // 아이디 (아이디 15Byte + 권한 1Byte)
    private char w_cat; // 경고분류 (0: 예약, 1: 게시판, 2: 댓글)
    private char w_reason; // 경고사유 (0 : 욕설, 1 : 도배, 2 : 광고, 3 : 부적절, 9: 기타)
    private String w_content; // 경고내용
}