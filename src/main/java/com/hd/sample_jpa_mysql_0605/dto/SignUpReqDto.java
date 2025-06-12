package com.hd.sample_jpa_mysql_0605.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter @Setter
@ToString
public class SignUpReqDto {
    private String name;
    private String email;
    private String pwd;
}
