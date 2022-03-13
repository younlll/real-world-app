package com.youn.realworldapp.service;

import com.youn.realworldapp.domain.User;
import com.youn.realworldapp.dto.LoginUserForm;
import com.youn.realworldapp.dto.RegistrationUserForm;
import com.youn.realworldapp.dto.UserResponseForm;
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

    public UserResponseForm loginUser(LoginUserForm userForm) {
        checkUserExistence(userForm.getEmail());
        User user = userRepository.findByEmail(userForm.getEmail()).get();
        if(!passwordEncoder.matches(userForm.getPassword(), user.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        return UserResponseForm.toDto(user);
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

    /**
     * 회원 가입여부 확인
     */
    private void checkUserExistence(String email) {
        if(!userRepository.existsByEmail(email)) {
            throw new IllegalStateException("존재하지 않는 이메일 주소입니다.");
        }
    }
}
