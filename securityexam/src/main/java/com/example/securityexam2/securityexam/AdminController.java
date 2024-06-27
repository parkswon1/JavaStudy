package com.example.securityexam2.securityexam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/abc")
    public String abc(){
        return "abc";
    }

    @GetMapping("/def") //admin, superuser접근
    public String def(){
        return "def";
    }

    @GetMapping("/list") //admin, superuser접근
    public String list(){
        return "list";
    }
}