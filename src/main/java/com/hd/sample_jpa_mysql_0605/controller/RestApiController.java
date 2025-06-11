package com.hd.sample_jpa_mysql_0605.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping("/hello")
    public String getHello(){
        return "안녕하세요 스- 입니다";
    }

    @GetMapping("/board/{variable}")
    public String getVariable(@PathVariable String variable){
        return variable;
    }

    @GetMapping("/req") // @RequestParam은 쿼리 형식으로 값ㅇ르 전달
    public String getReqParam(@RequestParam String name,
                              @RequestParam String email,
                              @RequestParam String company){
        return "이름 : " + name + " / 이메일 : " + email + " / " +company;
    }
}
