package com.example.springmvc.controller.Notuse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(Model model){
        model.addAttribute("welcomeMessage", "Welcome to our aswesome web");
        List<Item> items = Arrays.asList(
                new Item("Apple", 1.25),
                new Item("Banana", 0.75),
                new Item("Orange", 0.50)
        );
        model.addAttribute("items", items);
        return "welcome";
    }

    @AllArgsConstructor
    @Getter
    @Setter
    static class Item{
        private String name;
        private double price;
    }
}
