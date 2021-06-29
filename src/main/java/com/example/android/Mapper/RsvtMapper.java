package com.example.android.Mapper;


import com.example.android.Dto.Rsvt;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RsvtMapper {
    ArrayList<Rsvt> getTodaysRsvt(String chg_id, String stat_id); // 일정 일자 예약리스트

    ArrayList<Rsvt> getMyRsvt(String u_id); // 내 예약 조회

    int insertRsvt(Rsvt rsvt); // 예약하기

    void cancelRsvt(Rsvt rsvt);// 예약 취소

    Rsvt checkRsvt(String chg_id, String u_id);

    Rsvt getChargerMyRsvt(String stat_id, String chg_id, String u_id); // 어느 충전소 어느 충전기의 내 예약
}
