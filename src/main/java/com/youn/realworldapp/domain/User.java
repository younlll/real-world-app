package com.youn.realworldapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class User {

    private Long id;
    private String email;
    private String password;
    private String token;
    private String username;
    private String bio;
    private String image;
}
