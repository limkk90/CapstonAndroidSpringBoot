package com.example.android.Mapper;


import com.example.android.Dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {
    int join(User user); // 회원가입

    User login(User user); // 로그인

    void updateUser(User user); // 회원정보 수정

    User getProfile(String u_id); // 회원정보 조회

    User FindUser(String email); // 아이디 찾기

    int getPoint(String u_id); // 포인트 조회

    void updatePoint(String u_id, int point); // 포인트 수정

    Optional<User> getUser(String u_id); // JWT



}
