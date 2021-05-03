package com.example.android.Mapper;


import com.example.android.Dto.Marker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MapMapper {
    List<Marker> getMarkerList();
}
