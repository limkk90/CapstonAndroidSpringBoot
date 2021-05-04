package com.example.android.Mapper;


import com.example.android.Dto.Marker;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;


@Mapper
public interface MapMapper {
    ArrayList<Marker> getMarkerList();
}
