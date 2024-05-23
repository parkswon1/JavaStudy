package com.example.boardproject.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    @Id
    private Long Id;
    private String name;
    private String title;
    private String password;
    private String content;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
