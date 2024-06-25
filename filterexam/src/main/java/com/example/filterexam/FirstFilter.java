package com.example.filterexam;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@WebFilter(urlPatterns = "/hi")
public class FirstFilter implements Filter {
    public FirstFilter(){
        log.info("firstFilter 생성자 실행!!");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("FirstFilterinit 실행!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("FirstFilter dofilter 전 실행!");

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("FirstFilter dofitler 후 실행!");
    }

    @Override
    public void destroy() {
        log.info("FirstFilterdestoryt 실행!");
    }
}
