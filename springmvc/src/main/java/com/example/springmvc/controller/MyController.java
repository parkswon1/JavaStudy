package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/home")
    String hello(){
        return "home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}
