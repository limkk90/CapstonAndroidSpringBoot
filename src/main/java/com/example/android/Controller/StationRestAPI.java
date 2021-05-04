package com.example.android.Controller;


import com.example.android.Dto.Station;
import com.example.android.Service.StationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class StationRestAPI {

    @Autowired
    private StationService stationService;

//    @Autowired
//    private ReviewService reviewService;
//
//    @Autowired
//    private FacilityService facilityService;

    // 충전소 조회
    @GetMapping("/station")
    public Map<String, Object> getStation(Station station) {
        log.info("[GET StationAPI (/station)] STATION : " + station);

        List<Station> stat = stationService.getStation(station.getStat_lng(), station.getStat_lat());
        log.info("stat : " + stat.toString());
        if(stat.isEmpty()) throw new NullPointerException("해당 위치에 충전소 없음!");

        Map<String, Object> result = new HashMap<>();

        result.put("station", stat); // 충전소
//        result.put("facilityList", facilityService.getFacilityList(stat.get(0).getStat_lng(), stat.get(0).getStat_lat())); // 주변시설
//        result.put("reviewList", reviewService.getReviewList(stat.get(0).getStat_id())); // 리뷰

        return result;
    }

    // 충전소 리스트
    @GetMapping("/station/list")
    public List<Station> getStationList() {
        log.info("[GET StationAPI (/station/list)]");
        return stationService.getStationList();
    }

    // 충전소 추가
    @PutMapping("/station")
    public void insertStat(@RequestBody Station station) {
        log.info("[PUT StationAPI (/station)] Station: " + station);
        stationService.insertStat(station);
    }

    // 충전소 삭제
    @DeleteMapping("/station")
    public void deleteStat(@RequestBody Station station) {
        log.info("[DELETE StationAPI (/station)] Station_ID: " + station.getStat_id());
        stationService.deleteStat(station.getStat_id());
    }
}
