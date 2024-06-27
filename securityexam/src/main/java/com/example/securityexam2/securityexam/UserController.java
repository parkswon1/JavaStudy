package com.example.securityexam2.securityexam;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/mypage")
    public String mypage(@AuthenticationPrincipal UserDetails userDetails){
        return "username :: " + userDetails.getUsername();
    }
}
