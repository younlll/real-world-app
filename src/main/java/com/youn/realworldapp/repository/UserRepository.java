package com.youn.realworldapp.repository;


import com.youn.realworldapp.domain.RegistrationUserForm;
import com.youn.realworldapp.domain.User;

import java.util.List;

public interface UserRepository {

    // 사용자 등록
    RegistrationUserForm registrationUser(RegistrationUserForm userForm);

    // 사용자 이메일 조회
    List<User> inqUserEmail(String email);
}
