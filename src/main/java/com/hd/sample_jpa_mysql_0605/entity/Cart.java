package com.hd.sample_jpa_mysql_0605.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter @Setter
@ToString
public class Cart {
    @Id
    @Column(name="cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 128, nullable = false)
    private String cartName;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;
}
