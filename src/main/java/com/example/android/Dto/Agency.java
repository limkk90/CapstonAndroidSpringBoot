package com.example.android.Dto;

import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
public class Agency {
    private String agcy_id; // 기관아이디
    private String agcy_nm; // 운영기관명
    private String agcy_phone; // 전화번호
    private int agcy_bs_R; // 기본요금
    private int agcy_fee; // 이용수수료
    private int agcy_dc; // 할인율
    private Blob agcy_card; // 로고
    private LocalDateTime agcy_up_dtt; // 갱신일시
}
