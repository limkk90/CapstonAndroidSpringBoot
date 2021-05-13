package com.example.android.Mapper;


import com.example.android.Dto.Marker;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.List;
=======

>>>>>>> 997ac2cc128a20454f87b70c3746e23556e1b3f5

@Mapper
public interface MapMapper {
    ArrayList<Marker> getMarkerList();
}
