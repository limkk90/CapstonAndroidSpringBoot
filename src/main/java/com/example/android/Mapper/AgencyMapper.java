package com.example.android.Mapper;


import com.example.android.Dto.Agency;
import com.example.android.Dto.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AgencyMapper {
    List<Agency> AgencyList(Criteria criteria); // 기관 리스트

    Agency readAgency(String agcy_id); // 기관 조회

    void insertAgency(Agency agency); // 기관 추가

    void updateAgency(Agency agency); // 기관 수정

    void deleteAgency(Agency agency); // 기관 삭제

    int agencyListCnt(); // 페이지 수
}
