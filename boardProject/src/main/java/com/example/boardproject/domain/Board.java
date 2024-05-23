package com.example.boardproject.domain;

import com.mysql.cj.jdbc.result.UpdatableResultSet;
import lombok.*;
import org.springframework.cglib.core.Local;
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
    private LocalDateTime create_at;
    private LocalDateTime updated_at;
}
