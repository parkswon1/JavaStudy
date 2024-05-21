package com.example.springmvc.controller.Notuse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ExampleController {

    @GetMapping("/example")
    public String showExample(Model model){
        model.addAttribute("username", "jo");
        model.addAttribute("isAdmin", true);
        model.addAttribute("lang", new String[]{"E","S","G"});
        return  "example";
    }
}
