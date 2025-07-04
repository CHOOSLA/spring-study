package com.hd.sample_jpa_mysql_0605.controller;

import com.hd.sample_jpa_mysql_0605.dto.*;
import com.hd.sample_jpa_mysql_0605.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:5173"
})
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // 회ㅜ언 가입 여부 확인
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email){
        boolean isExist = authService.isMember(email);

        return ResponseEntity.ok(!isExist);
    }

    // 회원 가입
    @PostMapping("/signup") // Body에 정보를 싣는 방식, 정보가 보여지지 않음
    public ResponseEntity<MemberResDto> signup(@RequestBody MemberReqDto memberReqDto){
        return ResponseEntity.ok( authService.signUp(memberReqDto));
    }


    // 로그인
    // Post 방식 : email, pwd를 Request Body 형식으로 수신
    // 응답은 boolean 값으로 응답
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberReqDto requestDto) {
        return ResponseEntity.ok(authService.login(requestDto));
    }


    // 회원 조회
    // Get방식 : email이 있으면 해당 회원 조회
    // 없으면 전체 회원 조회
    // 단 회원 정보 리스트는 서비스로직과 DB가 없으므로 , for문으로 더미 생성

}
