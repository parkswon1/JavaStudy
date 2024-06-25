package com.example.filterexam;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@WebFilter(urlPatterns = "/user/*")
public class TestFilter implements Filter {
    public TestFilter(){
        log.info("test필터생성자 실행");
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("test필터init 실행");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("test필터dofilter 실행");
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("test필터dofilter 실행");
    }

    @Override
    public void destroy() {
        log.info("test필터init 실행");
    }
}
