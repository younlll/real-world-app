package com.youn.realworldapp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationUserForm {

    @Email(message = "이메일 형식에 맞지 않습니다.")
    @NotBlank(message = "이메일 입력은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호 입력은 필수입니다.")
    @Length(min = 6, max = 15, message = "비밀번호의 길이는 6자~15자입니다.")
    private String password;

    @NotBlank(message = "사용자 이름의 입력은 필수입니다.")
    private String username;
}
