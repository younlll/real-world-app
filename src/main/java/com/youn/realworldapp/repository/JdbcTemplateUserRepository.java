package com.youn.realworldapp.repository;

import com.youn.realworldapp.dto.RegistrationUserForm;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public RegistrationUserForm registrationUser(RegistrationUserForm userForm) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", userForm.getEmail());
        parameters.put("username", userForm.getUsername());
        parameters.put("password", userForm.getPassword());

        jdbcInsert.execute(parameters);
        return userForm;
    }

    @Override
    public boolean checkEmailExists(String email) {
        Integer inqResultCnt = jdbcTemplate.queryForObject("select count(*) from users\n" +
                "where email = ?\n" +
                "limit 1", Integer.class, email);
        return inqResultCnt <= 0;
    }
}
