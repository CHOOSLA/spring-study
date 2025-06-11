package com.hd.sample_jpa_mysql_0605.entity;

import com.hd.sample_jpa_mysql_0605.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@ToString // toString() 오러아비드 자동으로 해줌
@Entity // 해당 클래스가 엔티티임을 나타냄 즉, db테이블 생성
@Table(name="item") // 해당 클래스가 DB 테이블로 생성될 떄, 생성시
public class Item {
    @Id // 기분키 필드 저장, 유일한 값, not null 이건 반드시 있어야함
    @Column(name="item_id") // DB로 생성된 이름 지정
    @GeneratedValue(strategy = GenerationType.AUTO) // 기본키 생성 젼략, JPA가 DB에 맞게 생성전략 시행
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemNm;

    @Column(nullable = false, name = "price")
    private int price;

    @Column(nullable = false) // name이 없으면 stock_number
    private int stockNumber;

    @Lob // 대용량
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;


    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
