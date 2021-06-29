package com.example.android.Controller;


import com.example.android.Dto.Charger;
import com.example.android.Service.ChargerService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class ChargerRestAPI {

    @Autowired
    private ChargerService chargerService;


    // 특정 충전소 충전기 리스트
    @GetMapping("/{stat_id}/chargerlist")
    public ArrayList<Charger> getChargerList(@PathVariable("stat_id") String stat_id){
        log.info("getChargerListId:" + stat_id);
        log.info("getChargerList:" + chargerService.getChargerList(stat_id));
        return chargerService.getChargerList(stat_id);
    };

    // 충전기 추가
    @PutMapping("/charger")
    public void insertCharger(@RequestBody Charger charger) {
        log.info("[PUT ChargerAPI (/charger)] Charger: " + charger);
        chargerService.insertCharger(charger);
    }

    // 충전기 삭제
    @DeleteMapping("/charger")
    public void deleteCharger(@RequestBody Charger charger) {
        log.info("[DELETE ChargerAPI (/charger)] Charger: " + charger);
        chargerService.deleteCharger(charger);
    }
}
