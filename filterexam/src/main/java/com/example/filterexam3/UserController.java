package com.example.filterexam3;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/loginform")
    public String loginform() {
        return "loginform";
    }

    @PostMapping("/login")
    public String loginform(@ModelAttribute("user") User user, HttpServletResponse response) {

        //사용자가 보낸 username과 패스워드가 서버가 원하는 정보랑 일치하는지 확인하고
        //사용자 정보를 유지하게 하면 된다.
        if(user.getUsername().equals("park") && user.getPassword().equals("1234")){
            //실제 서비스에서는 아이디 암호를 검사해서 진행하겠지만 여기서는 임시방편으로 사용
            Cookie cookie = new Cookie("auth", "park");
            cookie.setPath("/");

            //이렇게 생성된 쿠키는 클라이언트에게 보내져야한다.
            response.addCookie(cookie);
        }
        return "redirect:/info";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/info")
    public String info(HttpServletRequest request){
        String auth = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals("auth")){
                    auth = cookie.getValue();
                    break;
                }
            }
        }
        if(auth != null){
            return "redirect:/welcome";
        }
        return "redirect:/loginform";
    }
}