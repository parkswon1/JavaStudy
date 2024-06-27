package com.example.securityexam;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .anyRequest() //모든 요청
                        .authenticated() //인증을 요구
                );
//                .formLogin(Customizer.withDefaults()); //form로그인을 사용할껀데 기본 form을 사용하겠다.
        http
                .formLogin(formLogin -> formLogin
//                        .loginPage("/loginForm") //로그인폼으로 로그인시킴
                        .defaultSuccessUrl("/success") //로그인 성공시 이동
                        .failureUrl("/fail") //로그인 실패시 이동
                                .successHandler((request, response, authentication) -> {
                                    log.info("authentication :: " + authentication.getName());
                                    response.sendRedirect("/success");
                                })
                                .failureHandler((request, response, exception) -> {
                                    log.info("exception::" + exception.getMessage());
                                    response.sendRedirect("/login");
                                })
//                        .usernameParameter("userId")
//                        .passwordParameter("password")
//                        .loginProcessingUrl("/login_p")
                        .permitAll() //로그인 페이지에 대한 요청은 누구나 요철 할 수 있도록 설정
                );

        http
                .logout(logout -> logout
                        .logoutUrl("/log_out")
                        .logoutSuccessUrl("/login")
                        .addLogoutHandler((request, response, authentication) -> {
                            log.info("logout ");
                            HttpSession session = request.getSession();
                            session.invalidate();
                        })
                        .logoutSuccessHandler((request, response, authentication) -> {
                            log.info("logoutSuccessHanser ");
                            response.sendRedirect("/logout");
                        })
                        .deleteCookies("remeber-me")
                );
        return http.build();
    }
}