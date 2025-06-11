package com.hd.sample_jpa_mysql_0605.repository;

import com.hd.sample_jpa_mysql_0605.entity.Cart;
import com.hd.sample_jpa_mysql_0605.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
@Transactional // 여러개의 자겅ㅂ을 한개의 논리적 작업 단위로 묶어줌.
@TestPropertySource(locations = "classpath:application-test.properties")
public class CartRepositoryTest {
    @Autowired // 의존성 주입을 받음, 생성자를 통한 의존성 주입인 경우는 어노테이션 피료없음
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext // JPA에 있는 엔티티 매니저
    EntityManager em;

    // 회원 엔티티 생성
    public Member createMember(){
        Member member = new Member();
        member.setEmail("example@example.com");
        member.setName("멤버1");
            member.setPwd("1234");
            member.setRegDate(LocalDateTime.now());

            return member;
    }

    @Test
    @DisplayName("장바구니와 회원 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setCartName("자동차 장바구니");
        cart.setMember(member);
        cartRepository.save(cart);



        em.flush(); // 영속성 컨텍스의 저장된에 내용을 DB에 반영
        em.clear(); // 영속성 컨텍스트 메모리 비웅

        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);

        log.info("Cart : " + saveCart);

    }
}
