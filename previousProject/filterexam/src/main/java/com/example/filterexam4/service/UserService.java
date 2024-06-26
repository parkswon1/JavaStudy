package com.example.filterexam4.service;

import com.example.filterexam4.entity.Role;
import com.example.filterexam4.entity.User;
import com.example.filterexam4.repository.RoleRepository;
import com.example.filterexam4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    //회원가입
    @Transactional(readOnly = false)
    public User registerNewUser(User user){
        //회원가입을 위해서 Role을 알아와서 Role 정보를 같이 넣어줘야해요.
        //이 메서드를 통해서 회원가입하는 회원은 일반 회원이라고 가정하고 ROLE_USER 로 Role을 얻어와서 저장해봅시다.
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(userRole));

        return userRepository.save(user);
    }

    //회원정보보기
    @Transactional(readOnly = true)
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}