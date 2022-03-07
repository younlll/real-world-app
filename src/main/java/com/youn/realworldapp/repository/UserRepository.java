package com.youn.realworldapp.repository;


import com.youn.realworldapp.dto.RegistrationUserForm;

public interface UserRepository {

    // 사용자 등록
    RegistrationUserForm registrationUser(RegistrationUserForm userForm);

    // 사용자 이메일 존재여부 확인을 위한 조회
    boolean checkEmailExists(String email);
}
