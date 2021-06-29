package com.example.android.Mapper;


import com.example.android.Dto.Charger;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ChargerMapper {
    ArrayList<Charger> getChargerList(String stat_id); // 특정 충전소의 충전기 리스트

    void insertCharger(Charger charger); // 충전기 추가

    void deleteCharger(Charger charger); // 충전기 삭제

    Charger chargerState(String stat_id, String chg_id); // 충전기 상태

    void updateState(String stat_id, String chg_id, String chg_st); // 상태코드 변경

    String isRservation(String stat_id, String chg_id); // 예약가능 여부
}
