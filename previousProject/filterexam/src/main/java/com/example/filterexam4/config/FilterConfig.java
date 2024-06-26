package com.example.filterexam4.config;

import com.example.filterexam4.filter.AuthenticationFilter;
import com.example.filterexam4.service.UserService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean //필터를 등록할때 @ 방법보다 config 방법을 이용하면 좀 더 직관적이고 디테일한 설정이 가능해요.
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter(UserService userService) {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setUserService(userService);

        registrationBean.setFilter(authenticationFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
