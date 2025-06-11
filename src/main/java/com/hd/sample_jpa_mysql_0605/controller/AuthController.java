package com.hd.sample_jpa_mysql_0605.controller;

import com.hd.sample_jpa_mysql_0605.dto.MemberReqDto;
import com.hd.sample_jpa_mysql_0605.dto.MemberResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/signup")
    public ResponseEntity<MemberResDto> signup(@RequestBody MemberReqDto memberReqDto){
      log.info("member : {}",memberReqDto);
      MemberResDto memberResDto = new MemberResDto();
      memberResDto.setEmail(memberReqDto.getEmail());
      memberResDto.setName(memberReqDto.getName());
      memberResDto.setPwd(memberReqDto.getPwd());
      memberResDto.setImage("/test/test.img");
      memberResDto.setRegDate(LocalDateTime.now());


      return ResponseEntity.ok(memberResDto);
    }


    // 로그인
    // Post 방식 : email, pwd를 Request Body 형식으로 수신
    // 응답은 boolean 값으로 응답


    // 회원 조회
    // Get방식 : email이 있으면 해당 회원 조회
    // 없으면 전체 회원 조회
    // 단 회원 정보 리스트는 서비스로직과 DB가 없으므로 , for문으로 더미 생성

}
