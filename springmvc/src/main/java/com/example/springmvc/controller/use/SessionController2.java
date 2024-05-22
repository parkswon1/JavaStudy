package com.example.springmvc.controller.use;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("visitCount")
public class SessionController2 {
    @GetMapping("/visit1")
    public String trackVisit(@ModelAttribute("visitCount") Integer visitCount, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();

        if (visitCount == null){
            visitCount = 0;
        }
        visitCount++;
        String sessionID = session.getId();
        model.addAttribute("sessionID", sessionID);
        model.addAttribute("visitCount", visitCount);
        return "visit2";
    }

    @GetMapping("/resetVisit1")
    public String resetVisit1(SessionStatus status) {
        status.setComplete(); // 세션 초기화
        return "redirect:/visit2";
    }
}

