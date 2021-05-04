package com.example.android.Service;


import com.example.android.Dto.Agency;
import com.example.android.Dto.Criteria;
import com.example.android.Mapper.AgencyMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AgencyService {

    @Autowired
    private AgencyMapper agencyMapper;

    // 기관 리스트
    public List<Agency> AgencyList(Criteria criteria) {
        return agencyMapper.AgencyList(criteria);
    }

    // 기관 조회
    public Agency readAgency(String agcy_id) {
        return agencyMapper.readAgency(agcy_id);
    }

    // 기관 추가
    public void insertAgency(Agency agency) {
        agencyMapper.insertAgency(agency);
    }

    // 기관 수정
    public void AgencyUpdate(Agency agency) {
        agencyMapper.updateAgency(agency);
    }

    // 기관 삭제
    public void AgencyDelete(Agency agency) {
        agencyMapper.deleteAgency(agency);
    }

    // 기관 총 수
    public int agencyListCnt(){
        return agencyMapper.agencyListCnt();
    }
}
