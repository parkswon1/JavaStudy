package com.example.filterexam2;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<UserFilter> authenticationFilter(){
        FilterRegistrationBean<UserFilter> registrationBean = new FilterRegistrationBean<>();
        UserFilter userFilter = new UserFilter();
        registrationBean.setFilter(userFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
