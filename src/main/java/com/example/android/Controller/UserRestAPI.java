package com.example.android.Controller;


import com.example.android.Dto.*;
import com.example.android.Service.EmailService;
import com.example.android.Service.JwtUserDetailsService;
import com.example.android.Service.UserService;
import com.example.android.config.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Log4j2
@RestController
@ResponseBody
@RequestMapping("/api")
public class UserRestAPI {
    @Autowired(required = false)
    UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;


    // 회원가입
    @PostMapping("/join")
    public String join(@RequestBody User user) {
        log.info("[POST UserAPI (/join)] User: " + user);
        userService.join(user);
        
        return "성공";
    }


    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        log.info("[POST UserAPI (/login)] JwtRequest: " + authenticationRequest);
        Map<String, Object> result = new HashMap<>();

        final User user = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getU_id(), authenticationRequest.getU_pwd());
        final String token = jwtTokenUtil.generateToken(user.getU_id());


        result.put("token", "Bearer " + token);
        result.put("userId", user.getU_id());
        result.put("userPoint", user.getU_point());

        return ResponseEntity.ok(result);
    }

    // android 로그인
    @PostMapping("/andLogin")
    public String andLogin(@RequestBody JwtRequest authenticationRequest) throws Exception {
        log.info("[POST UserAPI (/login)] JwtRequest: " + authenticationRequest);
        final User user = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getU_id(), authenticationRequest.getU_pwd());
        final String token = jwtTokenUtil.generateToken(user.getU_id());
        log.info("token = " + ResponseEntity.ok(new JwtResponse(token)).getBody());
        return ResponseEntity.ok(new JwtResponse(token)).getBody().getToken();
    }

    // 회원정보 수정
    @PostMapping("/update/{u_id}")
    public String update(@RequestBody User user) {
        log.info("[POST UserAPI (/{u_id}/update)] User: " + user);
        String secuPw = passwordEncoder.encode(user.getU_pwd());
        user.setU_pwd(secuPw);
        userService.updateUser(user);
        return "성공";
    }

    // 회원정보 수정(차량 추가)
    @PostMapping("/updated/{u_id}")
    public String update2(@RequestBody User user) {
        log.info("[POST UserAPI (/{u_id}/update)] User: " + user);
        String secuPw = passwordEncoder.encode(user.getU_pwd());
        user.setU_pwd(secuPw);
        userService.updateUser2(user);
        return "성공";
    }

    // 회원정보 조회
    @PostMapping("/profile/{u_id}")
    public User profile(@PathVariable String u_id) {
        log.info("[POST UserAPI (/{u_id}/profile)] User: " + u_id);
        return userService.getProfile(u_id);
    }

    // 아이디 찾기
    @PostMapping("/findId/{u_email}")
    public User findUser(@PathVariable String u_email) {
        log.info("유저아이디 찾기 테스트" + u_email);
        return userService.FindUser(u_email);
    }

    // 이메일

    // 메일 전송
    @PostMapping("/email")
    public String sendMail(@RequestBody Email email) throws Exception { // 이메일시 무조건 exception처리
        log.info("[POST UserAPI (/email)] Email: " + email);
        emailService.sendSimpleMessage(email.getUserEmail());
        return "성공";
    }

    // 인증 코드
    @PostMapping("/confirm")
    public int confirm(@RequestBody Email email) {
        if(EmailService.ePw.equals(email.getConfirm())){
            return EmailService.CONFIRM;
        }else {
            return EmailService.REJECT;
        }
    }

}

