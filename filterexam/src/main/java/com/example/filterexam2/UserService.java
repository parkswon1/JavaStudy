package com.example.filterexam2;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public void list(){
        User user = UserContext.getUser();

        System.out.println(user);
    }
}
