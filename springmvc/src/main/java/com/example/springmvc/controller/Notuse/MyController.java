package com.example.springmvc.controller.Notuse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/greeting")
    public ModelAndView greet(
            @RequestParam(name = "name", required = false, defaultValue = "Wd") String name){
        ModelAndView mav = new ModelAndView("greeting");
        mav.addObject("name", name);
        return mav;
    }
}
