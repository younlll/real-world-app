package com.youn.realworldapp.controller;

import com.youn.realworldapp.dto.RegistrationUserForm;
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

    @PostMapping("/api/user")
    public ResponseEntity<RegistrationUserForm> register(@RequestBody @Valid RegistrationUserForm userForm) {
        return ResponseEntity.ok(userService.registrationUser(userForm));
    }
}
