package com.youn.realworldapp.controller;

import com.youn.realworldapp.domain.RegistrationUserForm;
import com.youn.realworldapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<RegistrationUserForm> register(@RequestBody RegistrationUserForm userForm) {
        return ResponseEntity.ok(userService.registrationUser(userForm));
    }
}
