package com.example.jdbc02.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insertUser(User user) {
        jdbcTemplate.update("INSERT INTO users (name, email) VALUES (?, ?)", user.getName(), user.getEmail());
    }

    @Override
    public List<User> findAllUsers() {
        RowMapper<User> rowMapper = (rs, rowNum) -> new User(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("email")
        );
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    @Override
    public void updateUserEmail(String name, String email) {
        jdbcTemplate.update("UPDATE users SET email = ? WHERE name = ?", email, name);
    }

    @Override
    public void deleteUser(String name) {
        jdbcTemplate.update("DELETE from users WHERE name = ?", name);
    }
}