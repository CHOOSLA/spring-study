package com.hd.sample_jpa_mysql_0605.service;

import com.hd.sample_jpa_mysql_0605.dto.MemberReqDto;
import com.hd.sample_jpa_mysql_0605.dto.SignUpReqDto;
import com.hd.sample_jpa_mysql_0605.entity.Member;
import com.hd.sample_jpa_mysql_0605.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j // Log 정보를 출력하기 위한 롬복의 어노테이션
@Service // Spring Container에 Bean 등록
@Transactional // 여러 개의 물리적 작업 단위를 논리적 단위로 묶음
@RequiredArgsConstructor // 생성자를 자동 생성
public class AuthService {
    private final MemberRepository memberRepository; // 생성자를 통한 의존성 주입!!!!!!!!!!!


    // 회원 가입 여부 확인
    public boolean isMember(String email){
        return memberRepository.existsByEmail(email);
    }

    // 회원 가입
    public boolean signUp(SignUpReqDto signUpReqDto){
        try{
            Member member = convertDtoToEntity(signUpReqDto);
            memberRepository.save(member);
            return true;
        }catch (Exception e){
            log.error("회원 가입 시 오류 발생 : {}",e);
            return false;
        }
    }

    // 로그인
    public boolean login(String email, String pwd){
        Optional<Member> member = memberRepository.findByEmailAndPwd(email, pwd);

        return member.isPresent();
    }

    // DTO -> Entity Mapping
    private Member convertDtoToEntity(SignUpReqDto signUpReqDto){
        Member member = new Member();
        member.setEmail(signUpReqDto.getEmail());
        member.setPwd(signUpReqDto.getPwd());
        member.setName(signUpReqDto.getName());

        return member;
    }
}
