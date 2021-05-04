package com.example.android.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Charger {
    private String chg_id; // 충전기
    private char chg_type; // 충전기타입 (01:DC차데모, 02:AC완속, 03:DC차데모+AC3상, 04: DC콤보, 05:DC차데모+DC콤보, 06: DCckepah+AC3상+DC콤보, 07:AC3상)
    private String chg_method;
    private char chg_st;
    private LocalDateTime chg_st_dt;
    private char chg_rsvt;
    private String stat_id;
}
