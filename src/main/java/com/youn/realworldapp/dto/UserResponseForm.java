package com.youn.realworldapp.dto;

import com.youn.realworldapp.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseForm {

    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public static UserResponseForm toDto(User user) {
        return new UserResponseForm(user.getEmail(), user.getToken(), user.getUsername(), user.getBio(), user.getImage());
    }
}
