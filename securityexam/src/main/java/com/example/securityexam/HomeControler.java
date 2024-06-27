package com.example.securityexam;

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
}
