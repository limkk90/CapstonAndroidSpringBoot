package com.example.android.Service;


import com.example.android.Dto.Charger;
import com.example.android.Mapper.ChargerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

}
