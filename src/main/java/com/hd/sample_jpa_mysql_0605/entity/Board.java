package com.hd.sample_jpa_mysql_0605.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256, nullable = false)
    private String title;

    @Lob // 대용량 테스트
    private String content; // 게시글 내용
    private String image; // 게시글에 첨부될 이미지의 주소
    private LocalDateTime createTime;

    @PrePersist
    public void prePersist(){
        this.createTime = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY) // 연관관계 매핑
    @JoinColumn(name="member_id")
    private Member member;

}
