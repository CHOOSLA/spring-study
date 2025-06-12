package com.hd.sample_jpa_mysql_0605.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 게시글 쓰기
@Getter @Setter @ToString @NoArgsConstructor
public class BoardWriteDto {
    private String email;
    private String title;
    private String content;
    private String img;
}
