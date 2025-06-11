package com.hd.sample_jpa_mysql_0605.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LoginReqDto {
    private String email;
    private String pwd;
}
