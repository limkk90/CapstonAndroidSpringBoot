package com.example.android.Service;


import com.example.android.Dto.Charger;
import com.example.android.Mapper.ChargerMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
public class ChargerService {

    @Autowired(required = false)
    private ChargerMapper chargerMapper;

    // 특정 충전소 충전기 리스트
    public List<Charger> getChargerList(String stat_id){
        return chargerMapper.getChargerList(stat_id);
    };

    // 충전기 추가
    public void insertCharger(Charger charger) {
        chargerMapper.insertCharger(charger);
    }

    // 충전기 삭제
    public void deleteCharger(Charger charger) {
        chargerMapper.deleteCharger(charger);
    }

    // 충전기 상태 확인
    public Map<String, Character> chargerState(String stat_id, String chg_id) {

        Map<String, Character> map = new HashMap<>();

        Charger result = chargerMapper.chargerState(stat_id, chg_id);

        map.put("rsvt", result.getChg_rsvt());
        map.put("stat", result.getChg_st());

        return map;
    }

    // 상태코드 변경
    public void updateState(String stat_id, String chg_id, String chg_st) {
        chargerMapper.updateState(stat_id, chg_id, chg_st);
    }

    // 예약여부
    public String isRservation(String stat_id, String chg_id) {
        log.info("서비스에서 " + chargerMapper.isRservation(stat_id, chg_id));
        return chargerMapper.isRservation(stat_id, chg_id);
    }

}
