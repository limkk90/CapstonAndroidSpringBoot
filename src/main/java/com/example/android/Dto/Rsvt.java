package com.example.android.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Rsvt {
    private LocalDateTime rsvt_dtt; // 예약신청일시
    private String chg_id; // 충전기코드
    private String stat_id; // 충전소 코드
    private LocalDateTime rsvt_start; // 예약시작시간
    private LocalDateTime rsvt_end; // 예약종료시간
    private LocalDateTime rsvt_cancel; // 예약취소시간
    private String u_id; // 아이디

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime start; // 예약시작시간
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime end; // 예약종료시간
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime cancel; // 예약취소시간
}
