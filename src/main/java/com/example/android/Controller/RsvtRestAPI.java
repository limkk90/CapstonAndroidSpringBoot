package com.example.android.Controller;

import com.example.android.Dto.Rsvt;
import com.example.android.Service.ChargerService;
import com.example.android.Service.RsvtService;
import com.example.android.Service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class RsvtRestAPI {
    @Autowired
    UserService userService;

    @Autowired
    RsvtService rsvtService;

    @Autowired
    ChargerService chargerService;

    // 현재부터 24시 예약 조회
    @GetMapping("/todays-reservation")
    public List<Rsvt> TodaysRsvt(String chg_id, String stat_id) {
        log.info("[GET RsvtAPI (/todays-reservation)] CHG_ID: " + chg_id);
        log.info("[GET RsvtAPI (/todays-reservation)] STAT_ID: " + stat_id);
        return rsvtService.getTodaysRsvt(chg_id, stat_id);
    }

    @GetMapping("/{u_id}/reservation")
    public List<Rsvt> getMyRsvt(@PathVariable String u_id) {
        log.info("[GET RsvtAPI (/{u_id}/reservation)] U_ID: " + u_id);
        return rsvtService.getMyRsvt(u_id);
    }

    // 특정 충전소의 내 예약 조회
    @GetMapping("/{stat_id}/{chg_id}/{u_id}")
    public Rsvt getChargerMyRsvt(@PathVariable("stat_id") String stat_id,
                                 @PathVariable("chg_id") String chg_id,
                                 @PathVariable("u_id") String u_id) {
        log.info("[GET RsvtAPI (/{stat_id}/{chg_id}/{u_id})] stat_id: " + stat_id);
        log.info("[GET RsvtAPI (/{stat_id}/{chg_id}/{u_id})] chg_id: " + chg_id);
        log.info("[GET RsvtAPI (/{stat_id}/{chg_id}/{u_id})] u_id: " + u_id);

        return rsvtService.getChargerMyRsvt(stat_id, chg_id, u_id);
    }

    // 예약 하기
    @Transactional
    @PostMapping("/reservation")
    public Boolean reservation(@RequestBody Rsvt rsvt) {
        log.info("[POST RsvtAPI (/reservation)] Rsvt: " + rsvt);

//        // 매월 경고횟수 2회 이상일 시
//        if (userService.getWarningCnt(rsvt.getU_id()) > 1) {
//            // db에서 경고 받은것 중 마지막 경고의 날짜를 가져옴
//            WranDate w_date = userService.getLastWdateWithCurDate(rsvt.getU_id());
//            log.info("w_date : " + w_date.toString());
//
//            // 현재시간과 3일 더해준 시간을 비교해서 현재시간이 +3한 시간보다 이전이면 return false;
//            if (w_date.getD_date().after(new Date())) return false;
//        }

        // 예약전용 충전기가 아닐 시 예약 안됨
        if (chargerService.isRservation(rsvt.getStat_id(), rsvt.getChg_id()).equals("N")) return false;

        // 들어온 시작시간과 끝나는 시간이 최대 1시간까지 허용
        if (rsvt.getRsvt_end().isBefore(rsvt.getRsvt_start().plusHours(1L)) ||
                rsvt.getRsvt_end().isAfter(rsvt.getRsvt_start())) {
            List<Rsvt> list = rsvtService.getTodaysRsvt(rsvt.getChg_id(), rsvt.getStat_id());
            int result = rsvtService.insertRsvt(rsvt, list);

            rsvtService.rsvtTimeOut(rsvt); // 일정 시간 뒤 상태 체크해서 사용중이 아니면 예약 취소

            // 예약되면 1(true)  예약 안되면 0(false)을 반환
            return result > 0;
        }

        return false;
    }

    // 예약 취소
    @PatchMapping("/reservation")
    public void cancelRsvt(@RequestBody Rsvt rsvt) {
        log.info("[POST RsvtAPI (/{u_id}/reservation)] Rsvt: " + rsvt);
        rsvtService.cancelRsvt(rsvt);
    }




}
