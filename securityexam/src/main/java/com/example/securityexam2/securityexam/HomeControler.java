package com.example.securityexam2.securityexam;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControler {
    @GetMapping("/")
    public String home(){
        return "Home";
    }

    @GetMapping("/info")
    public String info(){
        return "info";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "LoginForm Page";
    }
    @GetMapping("/success")
    public String success(){
        return "success";
    }
    @GetMapping("/fail")
    public String fail(){
        return "fail";
    }

    @GetMapping("/test")
    public String test(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.isAuthenticated());
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal() instanceof String){
            return "익명사용자입니다.";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return "username ::: " + userDetails.getUsername();
    }
}
