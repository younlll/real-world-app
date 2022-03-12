package com.youn.realworldapp.service;

import com.youn.realworldapp.domain.User;
import com.youn.realworldapp.dto.RegistrationUserForm;
import com.youn.realworldapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 사용자 등록
     */
    public RegistrationUserForm registrationUser(RegistrationUserForm userForm) {
        User user = RegistrationUserForm.toEntity(userForm.getEmail(), userForm.getUsername(), passwordEncoder.encode(userForm.getPassword()));
        validateDuplicateUser(userForm.getEmail());
        return RegistrationUserForm.toDto(userRepository.registrationUser(user));
    }

    /**
     * 중복회원 검사
     * 동일한 email 주소로 가입이 불가
     */
    private void validateDuplicateUser(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new IllegalStateException("이미 가입된 이메일 주소입니다.");
        }
    }
}
