package com.example.filterexam2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping("/")
    public String home(){
        log.info("home() 실행");
        return "home";
    }

    @GetMapping("/hi")
    public String hi(){
        User user = UserContext.getUser();
        log.info(String.valueOf(user));
        return "hi";
    }
}
