package com.example.android.Service;


import com.example.android.Dto.Station;
import com.example.android.Mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {

    @Autowired(required = false)
    private StationMapper stationMapper;

    // 충전소 조회
    public List<Station> getStation(String stat_lng, String stat_lat) {
        return stationMapper.getStation(stat_lng, stat_lat);
    }

    // 충전소 리스트 조회
    public List<Station> getStationList() {
        return stationMapper.getStationList();
    }

    // 충전소 추가
    public void insertStat(Station station) {
        stationMapper.insertStat(station);
    }

    // 충전소 삭제
    public void deleteStat(String stat_id) {
        stationMapper.deleteStat(stat_id);
    }
}
