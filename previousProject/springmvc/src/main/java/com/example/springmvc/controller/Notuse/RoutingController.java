package com.example.springmvc.controller.Notuse;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {
    @GetMapping("/start")
    public String startProcess(Model model){
        model.addAttribute("forwardTest","pakr");
        return "forward:/forward";
    }

    @GetMapping("/forward")
    public String forward(Model model, HttpServletRequest request){
        System.out.println(request.getAttribute("forwardTest"));
        return "forwardPage";
    }

    @GetMapping("/redirect")
    public String redirect(Model model){
        model.addAttribute("forwardTest","pakr");
        return "redirect:/finalDestination";
    }

    @GetMapping("/finalDestination")
    public String finalDestination(Model model){
        System.out.println(model.getAttribute("forwardTest"));
        return "finalPage";
    }
}
