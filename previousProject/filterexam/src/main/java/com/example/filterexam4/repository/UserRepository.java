package com.example.filterexam4.repository;

import com.example.filterexam4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //id - 의미없는 값이므로..   우리는 username으로 찾아오는 메소드가 필요해요.
    User findByUsername(String username);
}