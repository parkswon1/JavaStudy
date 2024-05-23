package com.example.springjdbc01;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
}
