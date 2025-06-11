package com.hd.sample_jpa_mysql_0605.controller;

import com.hd.sample_jpa_mysql_0605.dto.SearchReqDto;
import com.hd.sample_jpa_mysql_0605.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class SearchMemberController {

    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMember(@RequestBody SearchReqDto searchReqDto){
        final List<Member> members= new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Member member = new Member();
            member.setId((long) i);
            member.setPwd("1234");
            member.setName("추창후르" + i);
            member.setEmail("test" + i + "@test.com");
            member.setImage("/test/test.img");
            members.add(member);
        }
        List<Member> result = members.stream()
                .filter(m -> m.getEmail().equals(searchReqDto.getEmail()))
                .toList();

        log.debug("werwe");
        if (result.isEmpty()) return ResponseEntity.ok(members);
        return ResponseEntity.ok(result);
    }
}
