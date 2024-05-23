package com.example.friend.controller;

import com.example.friend.entitiy.Friend;
import com.example.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendController {

    private final FriendService friendService;

    @GetMapping
    public List<Friend> getAllFriends() {
        return friendService.getAllFriends();
    }
}
