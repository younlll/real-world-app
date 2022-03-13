package com.youn.realworldapp.repository;

import com.youn.realworldapp.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User registrationUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("users").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("username", user.getUsername());
        parameters.put("password", user.getPassword());

        jdbcInsert.execute(parameters);

        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject("select exists(select 1\n" +
                "from users\n" +
                "where email = ?)", Boolean.class, email));
    }

    @Override
    public User findByEmail(String email) {
        return jdbcTemplate.queryForObject("select * from users where email = ?"
            , userRowMapper(), email
        );
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getLong("id")
                , rs.getString("email")
                , rs.getString("password")
                , rs.getString("token")
                , rs.getString("username")
                , rs.getString("bio")
                , rs.getString("image")
        );
    }
}
