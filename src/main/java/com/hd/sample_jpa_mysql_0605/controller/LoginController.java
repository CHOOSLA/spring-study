package com.hd.sample_jpa_mysql_0605.controller;


import com.hd.sample_jpa_mysql_0605.dto.LoginReqDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginReqDto loginReqDto){
        if(loginReqDto.getEmail()!=null && loginReqDto.getPwd() != null) return ResponseEntity.ok(true);
        return ResponseEntity.ok(false);
    }
}
