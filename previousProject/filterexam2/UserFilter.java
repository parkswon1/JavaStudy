package com.example.filterexam2;

import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //사용자가 요청하면서 보낸 값이 있다면 추출해서 UserContext에 저장하는 코드 구현
        try {
            User user = new User();
            user.setUsername("park");
            user.setPassword("1234");

            UserContext.setUser(user); //UserContext에 값을 저장 했을때 이 값을 어디서 사용할 수 있는지

            filterChain.doFilter(servletRequest, servletResponse);
        }finally {
            UserContext.clear();
        }
    }
}
