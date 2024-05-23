package com.example.springmvc.controller.Notuse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitController {

    @GetMapping("/visit")
    public String showVisit(
            @CookieValue(name = "lastVisit", defaultValue = "N/A") String lastVisit,
            HttpServletResponse response, Model model) {

        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String encodedTime;
        try {
            // 쿠키 값에 공백이나 특수 문자가 포함된 경우 URLEncoder를 사용하여 인코딩합니다.
            encodedTime = URLEncoder.encode(currentTime, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        // 쿠키 생성 및 설정
        Cookie cookie = new Cookie("lastVisit", encodedTime);
        cookie.setMaxAge(24 * 60 * 60); // 쿠키 유효기간 1일 설정
        response.addCookie(cookie);

        // 모델에 현재 시간과 이전 방문 시간 추가
        model.addAttribute("currentTime", currentTime);
        model.addAttribute("lastVisit", lastVisit);

        // visit.html로 반환
        return "visit";
    }
}