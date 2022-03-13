package com.youn.realworldapp.repository;

import com.youn.realworldapp.domain.User;

import java.util.Optional;

public interface UserRepository {

    // 사용자 등록
    User registrationUser(User user);

    // 사용자 이메일 존재여부 확인을 위한 조회
    boolean existsByEmail(String email);

    // 사용자 이메일을 이용한 사용자 정보 조회
    User findByEmail(String email);
}
