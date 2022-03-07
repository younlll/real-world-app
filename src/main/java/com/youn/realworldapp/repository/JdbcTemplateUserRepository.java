package com.youn.realworldapp.repository;

import com.youn.realworldapp.domain.RegistrationUserForm;
import com.youn.realworldapp.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
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
    public List<User> inqUserEmail(String email) {
        List<User> result = jdbcTemplate.query("select * from users\n" +
                "where email = ?", userRowMapper(), email);

        return result;
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setBio(rs.getString("bio"));

            return user;
        };
    }
}
