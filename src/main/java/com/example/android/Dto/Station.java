package com.example.android.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Station {
    private String stat_id;
    private String stat_nm;
    private String stat_addr;
    private String stat_lat;
    private String stat_lng;
    private String stat_commnet;
    private char stat_fee;
    private String stat_start;
    private String stat_end;
    private String agcy_id;
}
