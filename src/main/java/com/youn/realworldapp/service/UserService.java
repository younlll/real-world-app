package com.youn.realworldapp.service;

import com.youn.realworldapp.dto.RegistrationUserForm;
import com.youn.realworldapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 등록
     */
    public RegistrationUserForm registrationUser(RegistrationUserForm userForm) {
        validateDuplicateUser(userForm);
        return userRepository.registrationUser(userForm);
    }

    /**
     * 중복회원 검사
     * 동일한 email 주소로 가입이 불가
     */
    private void validateDuplicateUser(RegistrationUserForm userForm) {
        if(!userRepository.checkEmailExists(userForm.getEmail())) {
            throw new IllegalStateException("이미 가입된 이메일 주소입니다.");
        }
    }
}
