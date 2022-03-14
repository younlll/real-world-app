package com.youn.realworldapp.controller;

import com.youn.realworldapp.domain.User;
import com.youn.realworldapp.dto.LoginUserForm;
import com.youn.realworldapp.dto.RegistrationUserForm;
import com.youn.realworldapp.dto.UserResponseForm;
import com.youn.realworldapp.security.JwtTokenProvider;
import com.youn.realworldapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/api/users")
    public ResponseEntity<RegistrationUserForm> register(@RequestBody @Valid RegistrationUserForm userForm) {
        return ResponseEntity.ok(userService.registrationUser(userForm));
    }

    @PostMapping("/api/users/login")
    public ResponseEntity<UserResponseForm> login(@RequestBody @Valid LoginUserForm userForm) {
        User user = userService.loginUser(userForm);
        String token = jwtTokenProvider.createToken(userForm.getEmail());
        UserResponseForm userResponseForm = new UserResponseForm(user.getEmail(), token, user.getUsername(), user.getBio(), user.getImage());
        return ResponseEntity.ok(userResponseForm);
    }
}
