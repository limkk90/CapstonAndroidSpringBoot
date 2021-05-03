package com.example.android.Service;


import com.example.android.Dto.Marker;
import com.example.android.Mapper.MapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {
    @Autowired
    private MapMapper mapMapper;

    // 메인페이지 마커 리스트
    public List<Marker> getMarkerList() {
        return mapMapper.getMarkerList();
    }
}
