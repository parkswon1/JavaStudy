package com.example.friend.repository.logic;


import com.example.friend.entitiy.Friend;
import com.example.friend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendRepositoryImpl implements FriendRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Friend> findall() {
        return jdbcTemplate.query("SELECT * FROM friend", new BeanPropertyRowMapper<>(Friend.class));
    }

    @Override
    public Friend finfById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM friend WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Friend.class));
    }

    @Override
    public void save(Friend friend) {
        jdbcTemplate.update("INSERT INTO friend (name, email) VALUES (?, ?)", friend.getName(), friend.getEmail());
    }

    @Override
    public void update(Friend friend) {
        jdbcTemplate.update("UPDATE friend SET name = ?, email = ? WHERE id = ?", friend.getName(), friend.getEmail(), friend.getId());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM friend WHERE id = ?", id);
    }
}
