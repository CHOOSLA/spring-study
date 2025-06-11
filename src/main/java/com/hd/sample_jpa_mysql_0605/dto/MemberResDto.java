package com.hd.sample_jpa_mysql_0605.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResDto {
    private String email;
    private String pwd;
    private String name;
    private String image;
    private LocalDateTime regDate;


}
