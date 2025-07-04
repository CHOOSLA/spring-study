package com.hd.sample_jpa_mysql_0605.controller;

import com.hd.sample_jpa_mysql_0605.dto.MemberResDto;
import com.hd.sample_jpa_mysql_0605.dto.PageResponseDto;
import com.hd.sample_jpa_mysql_0605.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class PageController {
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<PageResponseDto<MemberResDto>> getMembers(
             @RequestParam(defaultValue = "0") int page,
             @RequestParam(defaultValue = "10") int size
    ){
       return ResponseEntity.ok(memberService.getMemberPageList(page , size));
    }

}
