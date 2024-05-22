package com.example.friend.service;

import com.example.friend.entitiy.Friend;

import java.util.List;

public interface FriendService {
    List<Friend> getAllFriends();
    Friend getFriendById(Long id);
    void addFriend(Friend friend);
    void updateFriend(Friend friend);
    void deleteFriend(Long id);
}
