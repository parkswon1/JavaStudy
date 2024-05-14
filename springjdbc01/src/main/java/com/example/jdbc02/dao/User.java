package com.example.jdbc02.dao;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
}
