package com.youn.realworldapp;


import com.youn.realworldapp.repository.JdbcTemplateUserRepository;
import com.youn.realworldapp.repository.UserRepository;
import com.youn.realworldapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final DataSource dataSource;

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new JdbcTemplateUserRepository(dataSource);
    }
}
