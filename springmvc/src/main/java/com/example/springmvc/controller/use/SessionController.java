package com.example.springmvc.controller.use;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {
    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);
    @GetMapping("/visit2")
    public String trackVisit(HttpSession session, HttpServletRequest request, Model model){
        Integer visitCount = (Integer) session.getAttribute("visitCount");
        String sessionID = session.getId();
        if (visitCount == null){
            visitCount = 0;
        }

        visitCount++;
        session.setAttribute("visitCount", visitCount);

        model.addAttribute("visitCount", visitCount);
        model.addAttribute("sessionID", sessionID);

        logger.info("Session ID: " + sessionID);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                logger.info("Cookie: " + cookie.getName() + "=" + cookie.getValue());
            }
        }

        return "visit2";
    }

    @GetMapping("/resetVisit2")
    public String resetVisit2(HttpSession session) {
        session.invalidate();
        return "redirect:/visit2";
    }

    @GetMapping("/removeAttrivute")
    public String removeAttribute(HttpSession session) {
        session.removeAttribute("visitCount");
        return "redirect:/visit2";
    }
}
