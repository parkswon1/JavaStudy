package com.example.friend.service.logic;

import com.example.friend.entitiy.Friend;
import com.example.friend.repository.FriendRepository;
import com.example.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public List<Friend> getAllFriends() {
        return friendRepository.findall();
    }

    @Override
    public Friend getFriendById(Long id) {
        return friendRepository.finfById(id);
    }

    @Override
    public void addFriend(Friend friend) {
        friendRepository.save(friend);
    }

    @Override
    public void updateFriend(Friend friend) {
        friendRepository.update(friend);
    }

    @Override
    public void deleteFriend(Long id) {
        friendRepository.delete(id);
    }
}
