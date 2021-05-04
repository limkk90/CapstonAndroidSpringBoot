package com.example.android.Controller;


import com.example.android.Dto.Agency;
import com.example.android.Dto.Criteria;
import com.example.android.Service.AgencyService;
import com.example.android.Service.Pagination;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class AgencyRestAPI {

    @Autowired
    private AgencyService agencyService;

    // 기관 리스트
    @GetMapping("/agency/list")
    public Map<String, Object> showMain(Criteria criteria,
                                        @RequestParam(defaultValue = "1") int page){
        log.info("[GET AgencyAPI (/agency/list)] page: " + page + ", Criteria: " + criteria);

        Map<String, Object> result = new HashMap<String, Object>();

        Pagination pagination = new Pagination(agencyService.agencyListCnt(), page, 10);
        criteria.setPage(pagination.getPage());

        result.put("page", page);
        result.put("pagination", pagination);
        result.put("agency", agencyService.AgencyList(criteria));

        return result;
    }

    // 기관 조회
    @GetMapping("/agency")
    public Map<String, Object> select(@RequestBody Agency agency , Criteria criteria){
        log.info("[GET AgencyAPI (/agency)] Agency: " + agency + ", Criteria: " + criteria);

        Map<String, Object> result = new HashMap<String, Object>();

        result.put("agency", agencyService.readAgency(agency.getAgcy_id()));
        result.put("criteria", criteria);

        return result;
    }

    // 기관 등록
    @PostMapping("/agency")
    public void insert(@RequestBody Agency agency) {
        log.info("[POST AgencyAPI (/agency)] Agency: " + agency);
        agencyService.insertAgency(agency);
    }

    // 기관 수정
    @PutMapping("/agency")
    public void update(@RequestBody Agency agency){
        log.info("[PUT AgencyAPI (/agency)] Agency: " + agency);
        agencyService.AgencyUpdate(agency);
    }

    // 기관 삭제
    @DeleteMapping("/agency")
    public void remove(@RequestBody Agency agency){
        log.info("[DELETE AgencyAPI (/agency)] Agency: " + agency);
        agencyService.AgencyDelete(agency);
    }
}
