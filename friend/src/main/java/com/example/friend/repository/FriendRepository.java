package com.example.friend.repository;

import com.example.friend.entitiy.Friend;

import java.util.List;

public interface FriendRepository {
    List<Friend> findall();
    Friend finfById(Long id);
    void save(Friend friend);
    void update(Friend friend);
    void delete(Long id);
}
