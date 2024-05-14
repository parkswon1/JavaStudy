package com.example.jdbc02.dao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringjdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringjdbcApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserDao userDao){
        return args -> {
            userDao.insertUser(new User(null,"park","aw@naver.com"));
        };
    }
}
