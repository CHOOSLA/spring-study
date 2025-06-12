package com.hd.sample_jpa_mysql_0605.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BoardModifyDto {
    private Long boardId;
    private String title;
    private String content;
    private String img;
}
