package com.example.android.Mapper;


import com.example.android.Dto.Charger;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChargerMapper {
    List<Charger>  getChargerList(String stat_id); // 특정 충전소의 충전기 리스트

    void insertCharger(Charger charger); // 충전기 추가

    void deleteCharger(Charger charger); // 충전기 삭제
}
