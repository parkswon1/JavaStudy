package com.example.filterexam4.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import com.example.filterexam4.entity.User;
import com.example.filterexam4.filter.UserContext;
import com.example.filterexam4.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/loginform")
    public String loginform(){
        return "loginform";
    }

    @PostMapping("/login")
    public String loginform(@ModelAttribute("user") User user, HttpServletResponse response) {

        //사용자가 보낸 username과 패스워드가 서버가 원하는 정보랑 일치하는지 확인하고
        //사용자 정보를 유지하게 하면 된다.
        User byUser = userService.findByUsername(user.getUsername());

        if(byUser != null && user.getPassword().equals(byUser.getPassword())){

            Cookie cookie = new Cookie("auth",user.getUsername());
            cookie.setPath("/");
            cookie.setHttpOnly(true); //자바스크립트로는 쿠키에 접근 할 수 없어요.

            //이렇게 생성된 쿠키는 클라이언에게 보내져야한다.
            response.addCookie(cookie);  //쿠키는 같은 이름의 쿠키가 2개 존재할 수 없어요..
            // 같은이름으로 쿠키가 다시 들어오면 쿠키는 덮어써요.
            //이것을 이용해서 로그아웃을 구현할 수 있어요.
            return "redirect:/welcome";
        }else {
            return "redirect:/loginform";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        //쿠키를 삭제하면 로그아웃이 되는데..
        //브라우저의 쿠키는 서버에서 삭제할 수는 없다.
        //그래서,  똑같은 이름의 쿠키를 만들어서 보낸다.
        Cookie cookie = new Cookie("auth","");
        cookie.setPath("/");
        cookie.setMaxAge(0);  //쿠키유지시간!!

        response.addCookie(cookie);

        return "redirect:/loginform";
    }
    @GetMapping("/welcome")
    public String welcome(){

        User user = UserContext.getUser();
        if(user != null)
            return "welcome";
        else
            return "redirect:/loginform";

//        return "welcome";

    }

    @GetMapping("/info")
    public String info(HttpServletRequest request){
        User user = UserContext.getUser();
        if(user != null)
            return "info";
        else
            return "redirect:/loginform";
    }

}
