package com.example.android.Mapper;


import com.example.android.Dto.Station;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StationMapper {

    List<Station> getStation(String stat_lng, String stat_lat); // 충전소 조회

    List<Station> getStationList(); // 충전소 리스트

    void insertStat(Station station); // 충전소 추가

    void deleteStat(String stat_id); // 충전소 삭제
}
