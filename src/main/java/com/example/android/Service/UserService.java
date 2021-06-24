package com.example.android.Service;


import com.example.android.Dto.User;
import com.example.android.Mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private PasswordEncoder passwordEncoder;

    //회원가입
    public void join(User user) {
        String rawPassword = user.getU_pwd();
        String encPassword = passwordEncoder.encode(rawPassword);

        user.setU_pwd(encPassword);

        userMapper.join(user);
    }

    // 로그인
    public User login(User user) {
        return userMapper.login(user);
    }

    // 회원정보 수정
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    // 회원정보 수정(차량수정 추가)
    public void updateUser2(User user) {userMapper.updateUser2(user);}

    // 회원정보 조회
    public User getProfile(String u_id) {
        return userMapper.getProfile(u_id);
    }

    // 아이디 찾기
    public User FindUser(String u_email) {
        return userMapper.FindUser(u_email);
    }

    public Optional<User> getUser(String u_id) {
        return userMapper.getUser(u_id);
    }





}
