package com.sparta.lv1_test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @Size(min = 4, max = 10, message = "아이디의 길이가 4자 이상 10자 이하로 구성되어야 합니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "조건에 맞지 않는 아이디 입니다.")
    private String username;
    @Size(min = 8, max = 15, message = "비밀번호의 길이가 8자 이상 15자 이하로 구성되어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z\\\\d`~!@#$%^&*()-_=+]{8,15}$", message = "형식에 맞지 않는 비밀번호 입니다.")
    private String password;
    @Email
    private String email;
    private boolean admin = false; // 관리자 권한 확인
    private String adminToken = ""; //관리자 토큰
}
