package com.hd.sample_jpa_mysql_0605.controller;

import com.hd.sample_jpa_mysql_0605.dto.DeleteMemberDto;
import com.hd.sample_jpa_mysql_0605.dto.MemberReqDto;
import com.hd.sample_jpa_mysql_0605.dto.MemberResDto;
import com.hd.sample_jpa_mysql_0605.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = {
        "http://localhost:3000",
        "http://localhost:5173"
})
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    // 회원 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<MemberResDto>> findAll(){
        return ResponseEntity.ok(memberService.findAll());
    }

    // 회원 토큰으로 전체 조회
    @GetMapping("/list")
    public ResponseEntity<List<MemberResDto>> memberList() {
        List<MemberResDto> list = memberService.getMemberList();
        return ResponseEntity.ok(list);
    }

    // 회원 상세 조회
    @GetMapping("/detail/{email}")
    public ResponseEntity<MemberResDto> userDetail(@PathVariable String email){
        return ResponseEntity.ok(memberService.findByEmail(email));
    }

    // 회원 정보 수정
//    @PostMapping("/modify")
//    public ResponseEntity<Boolean> modifyMember(@RequestBody MemberReqDto memberReqDto){
//        return ResponseEntity.ok(memberService.modifyMember(memberReqDto));
//    }

    // 회원 삭제
    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteMember(@RequestBody DeleteMemberDto deleteMemberDto){
        return ResponseEntity.ok(memberService.deleteMember(deleteMemberDto));
    }

}
