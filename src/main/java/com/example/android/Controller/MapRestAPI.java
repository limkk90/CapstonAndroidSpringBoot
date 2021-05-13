package com.example.android.Controller;


import com.example.android.Dto.Marker;
import com.example.android.Service.MapService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api/map")
public class MapRestAPI {
    @Autowired
    private MapService mapService;

    // 웹 메인페이지 지도 마커 리스트
    @GetMapping("/marker")
    public ArrayList<Marker> getMarkerList() {
        ArrayList<Marker> markerlist = mapService.getMarkerList();
//        log.info("되는기니" + markerlist);
        return markerlist;
    }
}