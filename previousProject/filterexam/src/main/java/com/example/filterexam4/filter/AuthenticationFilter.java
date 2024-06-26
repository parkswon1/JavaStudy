package com.example.filterexam4.filter;

import com.example.filterexam4.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import com.example.filterexam4.entity.User;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

public class AuthenticationFilter implements Filter {
    private UserService userService;
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            String path = request.getRequestURI(); //요청 주소
            String auth = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("auth")) {
                        auth = cookie.getValue();
                        break;
                    }
                }
            }
            if(auth != null) {
//                User user = new User();
//                user.setUsername(auth);
//
//                UserContext.setUser(user);
                User user = userService.findByUsername(auth);
                if(user != null){
                    if((path.equals("/admin") && user
                            .getRoles()
                            .stream()
                            .anyMatch(role -> role.getName().equals("ROLE_ADMIN")))
                            ||
                            (path.equals("/welcome") && user
                                    .getRoles()
                                    .stream()
                                    .anyMatch(role -> role.getName().equals("ROLE_USER")))
                    ){
                        filterChain.doFilter(servletRequest, servletResponse);
                        UserContext.clear();
                        return;
                    }
                }

                //권한이 없는 사용자
                if(path.equals("/admin")||path.equals("/info")){
                    response.sendRedirect("/access-denied");
                }else{
                    filterChain.doFilter(servletRequest, servletResponse);
                    UserContext.clear();
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            UserContext.clear();
        }
    }
}
