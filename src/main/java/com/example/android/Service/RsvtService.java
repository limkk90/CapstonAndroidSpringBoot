package com.example.android.Service;


import com.example.android.Dto.Charger;
import com.example.android.Dto.Rsvt;
import com.example.android.Mapper.ChargerMapper;
import com.example.android.Mapper.RsvtMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
@Log4j2
public class RsvtService {

    @Autowired(required = false)
    private RsvtMapper rsvtMapper;

    @Autowired
    private ChargerMapper chargerMapper;

    // 특정 충전소의 충전기의 내 예약 조회
    public Rsvt getChargerMyRsvt(String stat_id, String chg_id, String u_id) {
        return rsvtMapper.getChargerMyRsvt(stat_id, chg_id, u_id);
    }

    // 오늘 예약조회
    public ArrayList<Rsvt> getTodaysRsvt(String chg_id, String stat_id) {
        ArrayList<Rsvt> result = rsvtMapper.getTodaysRsvt(chg_id, stat_id);

        result.forEach(rsvt -> {
            rsvt.setStart(rsvt.getRsvt_start());
            rsvt.setEnd(rsvt.getRsvt_end());
            rsvt.setCancel(rsvt.getRsvt_cancel());
        });

        return result;
    }

    // 내 예약 조회
    public ArrayList<Rsvt> getMyRsvt(String u_id) {
        ArrayList<Rsvt> result = rsvtMapper.getMyRsvt(u_id);

        result.forEach(rsvt -> {
            rsvt.setStart(rsvt.getRsvt_start());
            rsvt.setEnd(rsvt.getRsvt_end());
            rsvt.setCancel(rsvt.getRsvt_cancel());
        });

        return result;
    }

    // 예약하기
    public int insertRsvt(Rsvt rsvt, List<Rsvt> list) {

        // 예약된 리스트가 있다면
        if(list.size() != 0) {
            // 예약 시간의 시작과 끝시간
            LocalDateTime start = rsvt.getRsvt_start();
            LocalDateTime end = rsvt.getRsvt_end();

            // Rsvt_start와 Rsvt_end 사이에 start혹은 end값이 있으면 예약이 있는걸로 보고 false를 반환
            for (Rsvt result : list) {
                if ((start.isAfter(result.getRsvt_start()) && start.isBefore(result.getRsvt_end())) ||
                        (end.isAfter(result.getRsvt_start()) && end.isBefore(result.getRsvt_end())) ||
                        start.isEqual(result.getRsvt_start()) ||
                        end.isEqual(result.getRsvt_start()) ||
                        start.isEqual(result.getRsvt_end()) ||
                        end.isEqual(result.getRsvt_end())) {
                    log.error("예약있다");
                    return 0;
                }
            }
        }

       return rsvtMapper.insertRsvt(rsvt);
    }

    // 예약 취소
    public void cancelRsvt(Rsvt rsvt) {
        rsvtMapper.cancelRsvt(rsvt);
    }

    // 예약 확인(Pi)
    public Rsvt checkRsvt(String chg_id, String u_id) {
        Rsvt rsvt = rsvtMapper.checkRsvt(chg_id, u_id);

        // 예약 시작 시간 + 10분 과 현재 시간 비교
        if(LocalDateTime.now().isBefore(rsvt.getRsvt_start().plusMinutes(10L))) {
            return rsvt;
        }

        return null;
    }

    // 예약시간 보다 10분 초과 시 자동취소
    public void rsvtTimeOut(Rsvt rsvt) {
        Timer timer = new Timer();
        TimerTask timeout = new TimerTask() {

            @Override
            public void run() {
                // 상태 체크해서 사용중이면 그대로 두고 사용중 아니면 예약 취소
                Charger result = chargerMapper.chargerState(rsvt.getStat_id(), rsvt.getChg_id());
                if(result.getChg_st() != '2') {
                    rsvtMapper.cancelRsvt(rsvt);
                }
            }
        };

        timer.schedule(timeout, 10 * 60 * 1000);
        // t.schedule(tt, 10 * 60 * 1000); // 지정된 시간 후에 실행 (분 * 초 * 미리초)
    }
}
