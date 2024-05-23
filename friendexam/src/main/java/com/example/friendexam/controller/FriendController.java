package com.example.friendexam.controller;

import com.example.friendexam.domain.Friend;
import com.example.friendexam.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    @GetMapping()
    public String friends(Model model){
        Iterable<Friend> friends = friendService.findAllFriends();
        model.addAttribute("friends", friends);
        return "friends/list";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("friend", new Friend());
        return "friends/form";
    }

    @PostMapping("/add")
    public String addFriend(@ModelAttribute Friend friend, RedirectAttributes redirectAttributes){
        friendService.saveFriend(friend);
        redirectAttributes.addFlashAttribute("message","친구등록 성공!");
        return "redirect:/friends";
    }

    @GetMapping("/{id}")
    public String detailFriend(Model model, @PathVariable Long id){
        Friend friend = friendService.findFriendById(id);
        model.addAttribute("friend",friend);
        return "friends/detail";
    }

    @GetMapping("/delete/{id}")
    public String deleteFriend(@PathVariable Long id, RedirectAttributes redirectAttributes){
        friendService.deleteFriendById(id);
        redirectAttributes.addFlashAttribute("message","친구삭제 성공!");
        return "redirect:/friends";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model){
        Friend friend = friendService.findFriendById(id);
        model.addAttribute("friend", friend);
        return "friends/edit";
    }

    @PostMapping("/update/{id}")
    public String editFriend(@PathVariable Long id, @ModelAttribute Friend friend, RedirectAttributes redirectAttributes){
        friend.setId(id);
        friendService.saveFriend(friend);
        redirectAttributes.addFlashAttribute("message","친구수정 성공!");
        return "redirect:/friends/{id}";
    }
}
