package com.example.friendexam.service;

import com.example.friendexam.domain.Friend;
import com.example.friendexam.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

//    public Iterable<Friend> findAllFriends(){
//        return friendRepository.findAll();
//    }

    @Transactional
    public Friend saveFriend(Friend friend){
        return friendRepository.save(friend);
    }

    @Transactional(readOnly = true)
    public Friend findFriendById(Long id){
        return friendRepository.findById(id).orElse(null);
    }

    public void deleteFriendById(Long id){
        friendRepository.deleteById(id);
    }

    public Page<Friend> findALLFriends(Pageable pageable) {
        Pageable sortedByDescId = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "id"));
        return friendRepository.findAll(sortedByDescId);
    }
}
