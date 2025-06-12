package com.hd.sample_jpa_mysql_0605.repository;

import com.hd.sample_jpa_mysql_0605.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    // 이메일 존재 여부 확인 : 존재하면 true
    boolean existsByEmail(String email);
    // 이메일로 회원 정보 가져 오기
    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPwd(String email, String pwd);
}
