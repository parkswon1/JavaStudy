package com.example.securityexam2.securityexam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers("/shop/**", "/test").permitAll() //지정 페이지 누구든지 접근 가능
                        .requestMatchers("/user/mypage").hasRole("USER")
                        .requestMatchers("/admin/abc").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "SUPER")
                        .anyRequest() //모든 요청
                        .authenticated() //인증을 요구
                )
                .formLogin(Customizer.withDefaults()); //form로그인을 사용할껀데 기본 form을 사용하겠다.


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDEtailsService(){
        //DB에 있는 사용자 정보를 이용할수 있도록 만들어야함
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("1234"))
                .roles("ADMIN")
                .roles("USER")
                .build();
        UserDetails superuser = User.withUsername("superuser")
                .password(passwordEncoder().encode("1234"))
                .roles("SUPER")
                .build();

        return new InMemoryUserDetailsManager(user,admin,superuser);
    }
}